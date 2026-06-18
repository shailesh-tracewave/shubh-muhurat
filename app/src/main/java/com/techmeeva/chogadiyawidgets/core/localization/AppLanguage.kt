package com.techmeeva.chogadiyawidgets.core.localization

enum class AppLanguage(val rawValue: String) {
    ENGLISH("en"),
    HINDI("hi"),
    GUJARATI("gu");

    val displayName: String
        get() = when (this) {
            ENGLISH -> "English"
            HINDI -> "हिंदी"
            GUJARATI -> "ગુજરાતી"
        }

    val localeIdentifier: String
        get() = when (this) {
            ENGLISH -> "en_IN"
            HINDI -> "hi_IN"
            GUJARATI -> "gu_IN"
        }

    val locale: java.util.Locale
        get() = when (this) {
            ENGLISH -> java.util.Locale("en", "IN")
            HINDI -> java.util.Locale("hi", "IN")
            GUJARATI -> java.util.Locale("gu", "IN")
        }

    companion object {
        fun fromRawValue(value: String): AppLanguage {
            return entries.firstOrNull { it.rawValue == value } ?: ENGLISH
        }
    }
}

enum class AppThemeMode(val rawValue: String) {
    SYSTEM("system"),
    LIGHT("light"),
    DARK("dark");

    fun displayName(language: AppLanguage): String {
        return when (language) {
            AppLanguage.ENGLISH -> when (this) {
                SYSTEM -> "System"
                LIGHT -> "Light"
                DARK -> "Dark"
            }
            AppLanguage.HINDI -> when (this) {
                SYSTEM -> "सिस्टम"
                LIGHT -> "लाइट"
                DARK -> "डार्क"
            }
            AppLanguage.GUJARATI -> when (this) {
                SYSTEM -> "સિસ્ટમ"
                LIGHT -> "લાઇટ"
                DARK -> "ડાર્ક"
            }
        }
    }

    fun subtitle(language: AppLanguage): String {
        return when (language) {
            AppLanguage.ENGLISH -> when (this) {
                SYSTEM -> "Follow your device setting"
                LIGHT -> "Warm, bright, and airy"
                DARK -> "Soft contrast for night viewing"
            }
            AppLanguage.HINDI -> when (this) {
                SYSTEM -> "डिवाइस सेटिंग के अनुसार"
                LIGHT -> "उज्ज्वल और हल्का दृश्य"
                DARK -> "रात में आरामदायक कंट्रास्ट"
            }
            AppLanguage.GUJARATI -> when (this) {
                SYSTEM -> "ડિવાઇસ સેટિંગ મુજબ"
                LIGHT -> "ઉજાસભર્યું અને હળવું દેખાવ"
                DARK -> "રાત માટે આરામદાયક કોન્ટ્રાસ્ટ"
            }
        }
    }

    companion object {
        fun fromRawValue(value: String): AppThemeMode {
            return entries.firstOrNull { it.rawValue == value } ?: SYSTEM
        }
    }
}

enum class AppTextKey {
    ACTIVE_NOW,
    FULL_SCHEDULE,
    TODAYS_PATH,
    HOME,
    CALENDAR,
    SETTINGS,
    SUNRISE,
    SUNSET,
    ENDS_AT,
    COULD_NOT_LOAD_TODAY_FLOW,
    COULD_NOT_LOAD_MONTH,
    TRY_AGAIN,
    PREMIUM_PREVIEW_TITLE,
    PREMIUM_PREVIEW_BODY,
    PREMIUM_UNLOCK_BODY,
    UPGRADE,
    ACTIVE,
    AUSPICIOUS_DAYS,
    DAY_NIGHT_FLOW,
    PREFERENCES,
    SYSTEM,
    LOCATION,
    USE_CURRENT_LOCATION,
    FINDING_CURRENT_CITY,
    SWITCH_TO_LIVE_LOCATION,
    LANGUAGE,
    APPEARANCE,
    CELESTIAL_LIGHT,
    RESTORE_PURCHASES,
    SYNC_PREMIUM_ACCESS,
    NOTIFICATIONS,
    COMING_SOON,
    RATE_APP,
    SHARE_APP,
    SHARE_APP_SUBTITLE,
    REPLAY_ONBOARDING,
    SEE_INTRODUCTION_AGAIN,
    PRIVACY_POLICY,
    TERMS_CONDITIONS,
    OPEN_LEGAL_PAGE,
    CURRENT_LOCATION_ACTIVE,
    PREMIUM_ACTIVE_TITLE,
    PREMIUM_INACTIVE_TITLE,
    PREMIUM_ACTIVE_BODY,
    PREMIUM_INACTIVE_BODY,
    SELECT_LOCATION,
    SEARCH_CITIES,
    CLOSE,
    OPEN_APP_TO_SYNC,
    CURRENT_SLOT,
    REMAINING,
    BRAND_TAGLINE,
    SPLASH_TITLE,
    SPLASH_SUBTITLE,
    NEXT,
    CONTINUE_ACTION,
    START_EXPLORING,
    ONBOARDING_WELCOME_TITLE,
    ONBOARDING_WELCOME_BODY,
    ONBOARDING_WIDGETS_TITLE,
    ONBOARDING_WIDGETS_BODY,
    ONBOARDING_OFFLINE_TITLE,
    ONBOARDING_OFFLINE_BODY,
    ONBOARDING_TIMELINE_TITLE,
    ONBOARDING_TIMELINE_BODY,
    ONBOARDING_LOCATION_TITLE,
    ONBOARDING_USE_CURRENT_LOCATION_BODY,
    DAYTIME,
    NIGHTTIME,
    UPGRADE_IN_APP
}
