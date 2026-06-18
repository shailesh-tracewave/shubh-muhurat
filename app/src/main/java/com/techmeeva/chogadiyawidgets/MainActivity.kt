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
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.techmeeva.chogadiyawidgets.core.state.EngagementManager
import com.techmeeva.chogadiyawidgets.core.state.EngagementSurface
import com.techmeeva.chogadiyawidgets.core.state.EngagementNotificationWorker

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appState: AppState
    private lateinit var engagementManager: EngagementManager

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val locationGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true || permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
        val notificationsGranted = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions[Manifest.permission.POST_NOTIFICATIONS] == true
        } else true

        if (!locationGranted) {
            showPermissionRationaleDialog()
        }
        
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
        
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        
        binding.bottomNav.setupWithNavController(navController)

        // Dynamically hide/show bottom navigation depending on onboarding state
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.onboardingFragment) {
                binding.bottomNav.visibility = View.GONE
                binding.bottomNavDivider.visibility = View.GONE
            } else {
                binding.bottomNav.visibility = View.VISIBLE
                binding.bottomNavDivider.visibility = View.VISIBLE
            }
        }
        // Navigate immediately if onboarding has already been completed
        if (appState.hasCompletedOnboarding) {
            val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
            navGraph.setStartDestination(R.id.homeFragment)
            navController.graph = navGraph
        }
        
        checkRequiredPermissions()
        checkRemoteConfig()
    }

    private fun checkRequiredPermissions() {
        val permissions = mutableListOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissions.add(Manifest.permission.POST_NOTIFICATIONS)
        }

        val missingPermissions = permissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        if (missingPermissions.isNotEmpty()) {
            requestPermissionLauncher.launch(missingPermissions.toTypedArray())
        }
    }

    private fun showPermissionRationaleDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Permission Required")
            .setMessage("Divine Choghadiya needs Location permission to calculate accurate timings for your city. Please grant this permission in settings.")
            .setCancelable(false)
            .setPositiveButton("Open Settings") { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", packageName, null)
                }
                startActivity(intent)
            }
            .setNegativeButton("Exit App") { _, _ ->
                finish()
            }
            .show()
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
                engagementManager.evaluate(config)
            }
        }
    }

    private fun showUpdateDialog(title: String, message: String, appStoreURL: String?, force: Boolean, targetVersion: String?) {
        val builder = MaterialAlertDialogBuilder(this)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(!force)
            .setPositiveButton("Update Now") { _, _ ->
                val url = appStoreURL ?: "market://details?id=$packageName"
                val intent = Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(url) }
                try {
                    startActivity(intent)
                } catch (e: Exception) {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
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
            .setTitle("Enjoying Divine Choghadiya?")
            .setMessage("If you love using the app, please take a moment to rate it. It helps us grow!")
            .setPositiveButton("Rate App") { _, _ ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
                try {
                    startActivity(intent)
                } catch (e: Exception) {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName")))
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
}
