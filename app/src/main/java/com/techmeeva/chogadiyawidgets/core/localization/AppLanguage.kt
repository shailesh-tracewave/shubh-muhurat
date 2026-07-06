package com.techmeeva.chogadiyawidgets.core.localization

import java.util.Locale

enum class AppLanguage(
    val rawValue: String,
    val displayName: String,
    val localeIdentifier: String
) {
    ENGLISH("en", "English", "en_IN"),
    ASSAMESE("as", "অসমীয়া", "as_IN"),
    BENGALI("bn", "বাংলা", "bn_IN"),
    BODO("brx", "बड़ो", "brx_IN"),
    DOGRI("doi", "डोगरी", "doi_IN"),
    GUJARATI("gu", "ગુજરાતી", "gu_IN"),
    HINDI("hi", "हिंदी", "hi_IN"),
    KANNADA("kn", "ಕನ್ನಡ", "kn_IN"),
    KASHMIRI("ks", "کٲشُر", "ks_IN"),
    KONKANI("kok", "कोंकणी", "kok_IN"),
    MAITHILI("mai", "मैथिली", "mai_IN"),
    MALAYALAM("ml", "മലയാളം", "ml_IN"),
    MANIPURI("mni", "মৈতৈলোন্", "mni_IN"),
    MARATHI("mr", "मराठी", "mr_IN"),
    NEPALI("ne", "नेपाली", "ne_IN"),
    ODIA("or", "ଓଡ଼ିଆ", "or_IN"),
    PUNJABI("pa", "ਪੰਜਾਬੀ", "pa_IN"),
    SANSKRIT("sa", "संस्कृतम्", "sa_IN"),
    SANTALI("sat", "ᱥᱟᱱᱛᱟᱲᱤ", "sat_IN"),
    SINDHI("sd", "سنڌي", "sd_IN"),
    TAMIL("ta", "தமிழ்", "ta_IN"),
    TELUGU("te", "తెలుగు", "te_IN"),
    URDU("ur", "اردو", "ur_IN");

    val localizedContentLanguage: LocalizedContentLanguage
        get() = when (this) {
            HINDI -> LocalizedContentLanguage.HINDI
            GUJARATI -> LocalizedContentLanguage.GUJARATI
            else -> LocalizedContentLanguage.ENGLISH
        }

    val locale: java.util.Locale
        get() = Locale.forLanguageTag(localeIdentifier.replace('_', '-'))

    companion object {
        fun fromRawValue(value: String): AppLanguage {
            return entries.firstOrNull { it.rawValue == value } ?: ENGLISH
        }

        fun defaultFromDevice(): AppLanguage {
            return fromLocale(Locale.getDefault())
        }

        fun fromLocale(locale: Locale): AppLanguage {
            val languageCode = locale.language.lowercase(Locale.US)
            return entries.firstOrNull { it.rawValue == languageCode } ?: ENGLISH
        }
    }
}

enum class LocalizedContentLanguage {
    ENGLISH,
    HINDI,
    GUJARATI
}

enum class AppThemeMode(val rawValue: String) {
    SYSTEM("system"),
    LIGHT("light"),
    DARK("dark");

    fun displayName(language: AppLanguage): String {
        return when (language.localizedContentLanguage) {
            LocalizedContentLanguage.ENGLISH -> when (this) {
                SYSTEM -> "System"
                LIGHT -> "Light"
                DARK -> "Dark"
            }
            LocalizedContentLanguage.HINDI -> when (this) {
                SYSTEM -> "सिस्टम"
                LIGHT -> "लाइट"
                DARK -> "डार्क"
            }
            LocalizedContentLanguage.GUJARATI -> when (this) {
                SYSTEM -> "સિસ્ટમ"
                LIGHT -> "લાઇટ"
                DARK -> "ડાર્ક"
            }
        }
    }

    fun subtitle(language: AppLanguage): String {
        return when (language.localizedContentLanguage) {
            LocalizedContentLanguage.ENGLISH -> when (this) {
                SYSTEM -> "Follow your device setting"
                LIGHT -> "Warm, bright, and airy"
                DARK -> "Soft contrast for night viewing"
            }
            LocalizedContentLanguage.HINDI -> when (this) {
                SYSTEM -> "डिवाइस सेटिंग के अनुसार"
                LIGHT -> "उज्ज्वल और हल्का दृश्य"
                DARK -> "रात में आरामदायक कंट्रास्ट"
            }
            LocalizedContentLanguage.GUJARATI -> when (this) {
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
