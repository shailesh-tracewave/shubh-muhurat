package com.techmeeva.chogadiyawidgets

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.techmeeva.chogadiyawidgets.core.localization.AppThemeMode
import com.techmeeva.chogadiyawidgets.core.network.RemoteConfigManager
import com.techmeeva.chogadiyawidgets.core.state.AppState
import com.techmeeva.chogadiyawidgets.core.state.WidgetUpdateWorker
import com.techmeeva.chogadiyawidgets.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.techmeeva.chogadiyawidgets.core.localization.AppLocalizer
import com.techmeeva.chogadiyawidgets.core.localization.AppTextKey
import com.techmeeva.chogadiyawidgets.core.state.AppConfiguration
import com.techmeeva.chogadiyawidgets.core.state.EngagementManager
import com.techmeeva.chogadiyawidgets.core.state.EngagementSurface
import com.techmeeva.chogadiyawidgets.core.state.EngagementNotificationWorker
import com.techmeeva.chogadiyawidgets.core.network.RemoteAppConfig

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appState: AppState
    private lateinit var engagementManager: EngagementManager
    private var latestRemoteConfig: RemoteAppConfig? = null
    private var hasEvaluatedEngagementThisSession = false

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val notificationsGranted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions[Manifest.permission.POST_NOTIFICATIONS] == true
        } else true
        
        if (notificationsGranted) {
            EngagementNotificationWorker.scheduleEngagementReminders(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_ChoghadiyaApp)
        appState = AppState(this)
        engagementManager = EngagementManager(this)
        engagementManager.recordLaunch()
        
        // Robust startup dark/light mode restoration
        when (appState.themeMode) {
            AppThemeMode.LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            AppThemeMode.DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            AppThemeMode.SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
        
        WidgetUpdateWorker.enqueueInitial(this)
        WidgetUpdateWorker.enqueuePeriodic(this)
        
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applySystemBarInsets()

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        
        binding.bottomNav.setupWithNavController(navController)
        updateBottomNavTitles()

        // Dynamically hide/show bottom navigation depending on onboarding state
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.onboardingFragment) {
                binding.bottomNav.visibility = View.GONE
                binding.bottomNavDivider.visibility = View.GONE
            } else {
                binding.bottomNav.visibility = View.VISIBLE
                binding.bottomNavDivider.visibility = View.VISIBLE
                evaluateEngagementIfReady()
            }
        }
        // Navigate immediately if onboarding has already been completed
        if (appState.hasCompletedOnboarding) {
            val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
            navGraph.setStartDestination(R.id.homeFragment)
            navController.graph = navGraph
        }
        
        checkRemoteConfig()
    }

    private fun checkRemoteConfig() {
        val remoteConfigManager = RemoteConfigManager(this)
        
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                engagementManager.surface.collect { surface ->
                    when (surface) {
                        is EngagementSurface.ForceUpdate -> {
                            showUpdateDialog(surface.title, surface.message, surface.appStoreURL, force = true, targetVersion = null)
                        }
                        is EngagementSurface.OptionalUpdate -> {
                            showUpdateDialog(surface.title, surface.message, surface.appStoreURL, force = false, targetVersion = surface.targetVersion)
                        }
                        is EngagementSurface.Rating -> {
                            showRatingDialog()
                        }
                        is EngagementSurface.Notification -> {
                            showNotificationDialog(surface.title, surface.message)
                        }
                        is EngagementSurface.Milestone -> {
                            showMilestoneDialog(surface.days, surface.title, surface.message)
                        }
                        null -> {}
                    }
                }
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            remoteConfigManager.refreshConfig()
            val config = remoteConfigManager.currentConfig
            withContext(Dispatchers.Main) {
                appState.applyRemoteConfig(config)
                latestRemoteConfig = config
                evaluateEngagementIfReady()
            }
        }
    }

    private fun applySystemBarInsets() {
        val navHostView = findViewById<View>(R.id.nav_host_fragment)
        val baseBottomNavHeight = (64 * resources.displayMetrics.density).toInt()
        val bottomNavPaddingLeft = binding.bottomNav.paddingLeft
        val bottomNavPaddingTop = binding.bottomNav.paddingTop
        val bottomNavPaddingRight = binding.bottomNav.paddingRight
        val bottomNavPaddingBottom = binding.bottomNav.paddingBottom

        ViewCompat.setOnApplyWindowInsetsListener(binding.mainLayout) { _, insets ->
            val statusBarTop = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            val navigationBarBottom = insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom

            navHostView.setPadding(
                navHostView.paddingLeft,
                statusBarTop,
                navHostView.paddingRight,
                navHostView.paddingBottom
            )
            binding.bottomNav.setPadding(
                bottomNavPaddingLeft,
                bottomNavPaddingTop,
                bottomNavPaddingRight,
                bottomNavPaddingBottom + navigationBarBottom
            )

            val targetBottomNavHeight = baseBottomNavHeight + navigationBarBottom
            if (binding.bottomNav.layoutParams.height != targetBottomNavHeight) {
                binding.bottomNav.layoutParams = binding.bottomNav.layoutParams.apply {
                    height = targetBottomNavHeight
                }
            }

            insets
        }
        ViewCompat.requestApplyInsets(binding.mainLayout)
    }

    private fun evaluateEngagementIfReady() {
        val config = latestRemoteConfig ?: return
        if (!appState.hasCompletedOnboarding || hasEvaluatedEngagementThisSession) return
        hasEvaluatedEngagementThisSession = true
        engagementManager.evaluate(config)
    }

    private fun showUpdateDialog(title: String, message: String, appStoreURL: String?, force: Boolean, targetVersion: String?) {
        val builder = MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(!force)
            .setPositiveButton("Update Now") { _, _ ->
                val url = androidStoreURL(appStoreURL)
                val intent = Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(url) }
                try {
                    startActivity(intent)
                } catch (e: Exception) {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(AppConfiguration.playStoreURL)))
                }
                if (!force) engagementManager.dismiss()
            }
            
        if (!force && targetVersion != null) {
            builder.setNegativeButton("Skip") { _, _ ->
                engagementManager.skipUpdate(targetVersion)
            }
        }
        builder.show()
    }

    private fun showRatingDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Enjoying Shubh Muhurat?")
            .setMessage("If you love using the app, please take a moment to rate it. It helps us grow!")
            .setPositiveButton("Rate App") { _, _ ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
                try {
                    startActivity(intent)
                } catch (e: Exception) {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(AppConfiguration.playStoreURL)))
                }
                engagementManager.markRatingPrompted()
            }
            .setNegativeButton("Later") { _, _ -> engagementManager.markRatingPrompted() }
            .show()
    }

    private fun showNotificationDialog(title: String, message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Allow") { _, _ ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                        EngagementNotificationWorker.scheduleEngagementReminders(this)
                        engagementManager.markNotificationPrompted()
                    } else {
                        // The RequestMultiplePermissions launcher will handle this
                        requestPermissionLauncher.launch(arrayOf(Manifest.permission.POST_NOTIFICATIONS))
                    }
                } else {
                    EngagementNotificationWorker.scheduleEngagementReminders(this)
                    engagementManager.markNotificationPrompted()
                }
            }
            .setNegativeButton("Maybe Later") { _, _ -> engagementManager.markNotificationPrompted() }
            .show()
    }

    private fun showMilestoneDialog(days: Int, title: String, message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage("$message\n\nYou've been using the app for $days days!")
            .setPositiveButton("Continue") { _, _ -> engagementManager.markMilestoneShown(days) }
            .show()
    }

    private fun updateBottomNavTitles() {
        val language = appState.selectedLanguage
        binding.bottomNav.menu.findItem(R.id.homeFragment)?.title = AppLocalizer.text(AppTextKey.HOME, language)
        binding.bottomNav.menu.findItem(R.id.calendarFragment)?.title = AppLocalizer.text(AppTextKey.CALENDAR, language)
        binding.bottomNav.menu.findItem(R.id.skyFragment)?.title = when (language) {
            com.techmeeva.chogadiyawidgets.core.localization.AppLanguage.ENGLISH -> "Sky"
            com.techmeeva.chogadiyawidgets.core.localization.AppLanguage.HINDI -> "आकाश"
            com.techmeeva.chogadiyawidgets.core.localization.AppLanguage.GUJARATI -> "આકાશ"
        }
        binding.bottomNav.menu.findItem(R.id.settingsFragment)?.title = AppLocalizer.text(AppTextKey.SETTINGS, language)
    }

    private fun androidStoreURL(remoteURL: String?): String {
        val candidate = remoteURL?.trim().orEmpty()
        return if (candidate.contains("play.google.com") || candidate.startsWith("market://")) {
            candidate
        } else {
            AppConfiguration.playStoreURL
        }
    }
}
