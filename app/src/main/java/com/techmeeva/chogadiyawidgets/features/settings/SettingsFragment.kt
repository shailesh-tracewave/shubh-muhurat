package com.techmeeva.chogadiyawidgets.features.settings

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.techmeeva.chogadiyawidgets.R
import com.techmeeva.chogadiyawidgets.core.localization.AppLanguage
import com.techmeeva.chogadiyawidgets.core.localization.AppThemeMode
import com.techmeeva.chogadiyawidgets.core.localization.AppLocalizer
import com.techmeeva.chogadiyawidgets.core.localization.AppTextKey
import com.techmeeva.chogadiyawidgets.core.state.AppState
import com.techmeeva.chogadiyawidgets.databinding.FragmentSettingsBinding
import com.techmeeva.chogadiyawidgets.models.SeedCity
import java.util.Locale
import com.techmeeva.chogadiyawidgets.models.LocationMode
import com.techmeeva.chogadiyawidgets.models.SubscriptionPlan

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var appState: AppState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appState = AppState(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateSettingsUI()

        // Preferences rows click listeners
        binding.rowLanguage.setOnClickListener {
            showLanguageDialog()
        }

        binding.rowLocation.setOnClickListener {
            showLocationDialog()
        }

        binding.rowAppearance.setOnClickListener {
            showAppearanceDialog()
        }

        // Extras click listeners
        binding.rowReplayOnboarding.setOnClickListener {
            appState.hasCompletedOnboarding = false
            findNavController().navigate(R.id.onboardingFragment)
        }

        binding.rowShareApp.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, AppLocalizer.text(AppTextKey.SPLASH_TITLE, appState.selectedLanguage))
                putExtra(Intent.EXTRA_TEXT, "${AppLocalizer.text(AppTextKey.BRAND_TAGLINE, appState.selectedLanguage)}\nDownload now to explore auspicious timings!")
            }
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }

        binding.rowRateApp.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse("market://details?id=${requireContext().packageName}"))
            try {
                startActivity(intent)
            } catch (e: Exception) {
                startActivity(Intent(Intent.ACTION_VIEW, android.net.Uri.parse("https://play.google.com/store/apps/details?id=${requireContext().packageName}")))
            }
        }

        binding.rowPrivacyPolicy.setOnClickListener {
            val url = "https://techmeeva.com/privacy-policy" // Standard fallback privacy URL
            val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse(url))
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateSettingsUI() {
        val language = appState.selectedLanguage

        // 1. Static Text & Brand Localization
        binding.tvSettingsTitle.text = AppLocalizer.text(AppTextKey.SETTINGS, language)
        binding.tvSettingsBrandTitle.text = "Divine Choghadiya"
        binding.tvSettingsBrandTagline.text = AppLocalizer.text(AppTextKey.BRAND_TAGLINE, language)

        binding.tvPrefHeader.text = AppLocalizer.text(AppTextKey.PREFERENCES, language).uppercase(Locale.US)
        binding.tvSupportHeader.text = AppLocalizer.text(AppTextKey.SYSTEM, language).uppercase(Locale.US) + " & INFO"

        binding.tvLangTitle.text = AppLocalizer.text(AppTextKey.LANGUAGE, language)
        binding.tvLocTitle.text = AppLocalizer.text(AppTextKey.LOCATION, language)
        binding.tvThemeTitle.text = AppLocalizer.text(AppTextKey.APPEARANCE, language)
        
        binding.tvReplayTitle.text = AppLocalizer.text(AppTextKey.REPLAY_ONBOARDING, language)
        binding.tvReplayValue.text = AppLocalizer.text(AppTextKey.SEE_INTRODUCTION_AGAIN, language)

        binding.tvShareTitle.text = AppLocalizer.text(AppTextKey.SHARE_APP, language)
        binding.tvShareValue.text = AppLocalizer.text(AppTextKey.SHARE_APP_SUBTITLE, language)

        // 2. Preferences dynamic values
        binding.tvLangValue.text = appState.selectedLanguage.displayName
        binding.tvLocValue.text = if (appState.locationMode == LocationMode.CURRENT) {
            "Current Location (${appState.selectedCity.city})"
        } else {
            appState.selectedCity.city
        }
        binding.tvThemeValue.text = appState.themeMode.displayName(language)
    }

    private fun showLanguageDialog() {
        val languages = arrayOf(
            AppLanguage.ENGLISH,
            AppLanguage.HINDI,
            AppLanguage.GUJARATI
        )
        val items = languages.map { it.displayName }.toTypedArray()
        val checkedItem = languages.indexOf(appState.selectedLanguage)

        AlertDialog.Builder(requireContext())
            .setTitle(AppLocalizer.text(AppTextKey.LANGUAGE, appState.selectedLanguage))
            .setSingleChoiceItems(items, checkedItem) { dialog, which ->
                appState.selectedLanguage = languages[which]
                dialog.dismiss()
                requireActivity().recreate() // Enforces instant UI reload in new language
            }
            .setNegativeButton(AppLocalizer.text(AppTextKey.CLOSE, appState.selectedLanguage), null)
            .show()
    }

    private fun showLocationDialog() {
        val cities = appState.availableCities
        val options = mutableListOf<String>()
        
        options.add("Current Location (GPS)")
        options.addAll(cities.map { "${it.city}, ${it.state}" })

        val checkedItem = if (appState.locationMode == LocationMode.CURRENT) {
            0
        } else {
            val idx = cities.indexOfFirst { it.id == appState.selectedCity.id }
            if (idx != -1) idx + 1 else -1
        }

        AlertDialog.Builder(requireContext())
            .setTitle(AppLocalizer.text(AppTextKey.LOCATION, appState.selectedLanguage))
            .setSingleChoiceItems(options.toTypedArray(), checkedItem) { dialog, which ->
                if (which == 0) {
                    // Set Current GPS mode, default to Ahmedabad fallback coordinates first, then auto-update when home/calendar loads
                    appState.applyCurrentLocation(cities.firstOrNull() ?: SeedCity.ahmedabadFallback)
                } else {
                    appState.selectCity(cities[which - 1])
                }
                dialog.dismiss()
                updateSettingsUI()
                Toast.makeText(requireContext(), "Location updated successfully!", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton(AppLocalizer.text(AppTextKey.CLOSE, appState.selectedLanguage), null)
            .show()
    }

    private fun showAppearanceDialog() {
        val themes = arrayOf(
            AppThemeMode.SYSTEM,
            AppThemeMode.LIGHT,
            AppThemeMode.DARK
        )
        val items = themes.map { it.displayName(appState.selectedLanguage) }.toTypedArray()
        val checkedItem = themes.indexOf(appState.themeMode)

        AlertDialog.Builder(requireContext())
            .setTitle(AppLocalizer.text(AppTextKey.APPEARANCE, appState.selectedLanguage))
            .setSingleChoiceItems(items, checkedItem) { dialog, which ->
                val selectedTheme = themes[which]
                appState.themeMode = selectedTheme
                dialog.dismiss()

                // Apply theme mode change
                when (selectedTheme) {
                    AppThemeMode.LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    AppThemeMode.DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    AppThemeMode.SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                }
            }
            .setNegativeButton(AppLocalizer.text(AppTextKey.CLOSE, appState.selectedLanguage), null)
            .show()
    }
}
