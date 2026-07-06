package com.techmeeva.chogadiyawidgets.core.state

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.techmeeva.chogadiyawidgets.core.localization.AppLanguage
import com.techmeeva.chogadiyawidgets.core.localization.AppThemeMode
import com.techmeeva.chogadiyawidgets.core.network.RemoteAppConfig
import com.techmeeva.chogadiyawidgets.models.LocationMode
import com.techmeeva.chogadiyawidgets.models.SeedCity
import com.techmeeva.chogadiyawidgets.models.SubscriptionPlan
import java.net.URL

class AppState(context: Context) {

    private val defaults: SharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    val availableCities: List<SeedCity> = SeedCity.loadFromAssets(context)
    private val fallbackCity = availableCities.firstOrNull() ?: SeedCity.ahmedabadFallback

    var hasCompletedOnboarding: Boolean
        get() = defaults.getBoolean(Keys.onboarding, false)
        set(value) = defaults.edit().putBoolean(Keys.onboarding, value).apply()

    var selectedLanguage: AppLanguage
        get() = defaults.getString(Keys.language, null)?.let { AppLanguage.fromRawValue(it) }
            ?: AppLanguage.defaultFromDevice()
        set(value) = defaults.edit().putString(Keys.language, value.rawValue).apply()

    var subscriptionPlan: SubscriptionPlan
        get() = AppConfiguration.defaultSubscriptionPlan
            ?: SubscriptionPlan.fromRawValue(defaults.getString(Keys.subscriptionPlan, SubscriptionPlan.FREE.rawValue) ?: "")
        set(value) = defaults.edit().putString(Keys.subscriptionPlan, value.rawValue).apply()

    var locationMode: LocationMode
        get() = LocationMode.fromRawValue(defaults.getString(Keys.locationMode, LocationMode.MANUAL.rawValue) ?: "")
        set(value) = defaults.edit().putString(Keys.locationMode, value.rawValue).apply()

    var themeMode: AppThemeMode
        get() = AppThemeMode.fromRawValue(defaults.getString(Keys.themeMode, AppThemeMode.SYSTEM.rawValue) ?: "")
        set(value) = defaults.edit().putString(Keys.themeMode, value.rawValue).apply()

    var selectedCity: SeedCity
        get() {
            val json = defaults.getString(Keys.selectedCity, null) ?: return fallbackCity
            return try {
                gson.fromJson(json, SeedCity::class.java)
            } catch (e: Exception) {
                fallbackCity
            }
        }
        set(value) {
            val json = gson.toJson(value)
            defaults.edit().putString(Keys.selectedCity, json).apply()
        }

    var apiBaseURL: String
        get() = defaults.getString(Keys.apiBaseURL, AppConfiguration.apiBaseURL) ?: AppConfiguration.apiBaseURL
        set(value) = defaults.edit().putString(Keys.apiBaseURL, value).apply()

    val isPremium: Boolean
        get() = if (!AppConfiguration.premiumServicesEnabled) true else subscriptionPlan.isPremium

    fun selectCity(city: SeedCity) {
        selectedCity = city
        locationMode = LocationMode.MANUAL
        defaults.edit().putString(Keys.locationMode, locationMode.rawValue).apply()
    }

    fun applyCurrentLocation(city: SeedCity) {
        selectedCity = city
        locationMode = LocationMode.CURRENT
        defaults.edit().putString(Keys.locationMode, locationMode.rawValue).apply()
    }

    fun applyRemoteConfig(config: RemoteAppConfig) {
        val remoteURL = validatedAPIBaseURL(config.apiBaseURL) ?: return
        if (remoteURL != apiBaseURL) {
            apiBaseURL = remoteURL
        }
    }

    private fun validatedAPIBaseURL(value: String?): String? {
        val trimmed = value?.trim().orEmpty()
        if (trimmed.isEmpty()) return null
        return try {
            val url = URL(trimmed)
            if (url.protocol.equals("https", ignoreCase = true) && !url.host.isNullOrBlank()) {
                trimmed.trimEnd('/')
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    private object Keys {
        const val onboarding = "app.onboarding.completed"
        const val selectedCity = "app.selected.city"
        const val language = "app.language"
        const val subscriptionPlan = "app.subscription.plan"
        const val locationMode = "app.location.mode"
        const val themeMode = "app.theme.mode"
        const val apiBaseURL = "app.api.base.url"
    }
}
