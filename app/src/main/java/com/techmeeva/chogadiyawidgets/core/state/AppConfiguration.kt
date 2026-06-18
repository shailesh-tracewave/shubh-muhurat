package com.techmeeva.chogadiyawidgets.core.state

import com.techmeeva.chogadiyawidgets.models.SubscriptionPlan

object AppConfiguration {
    const val premiumServicesEnabled = false
    val forcedSubscriptionPlanForQA: SubscriptionPlan? = if (premiumServicesEnabled) null else SubscriptionPlan.PREMIUM_YEARLY

    const val apiBaseURL = "https://choghadiya.ipotrending.com"
    const val remoteConfigURL = "https://chogadiya-divine.web.app/app_config.json"
    
    const val websiteURL = "https://chogadiya-divine.web.app"
    const val playStoreURL = "https://play.google.com/store/apps/details?id=com.techmeeva.chogadiyawidgets"
    const val privacyPolicyURL = "https://chogadiya-divine.web.app/privacy.html"
    const val termsURL = "https://chogadiya-divine.web.app/terms.html"
}
