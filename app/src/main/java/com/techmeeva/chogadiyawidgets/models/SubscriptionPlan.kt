package com.techmeeva.chogadiyawidgets.models

enum class SubscriptionPlan(val rawValue: String) {
    FREE("free"),
    PREMIUM_MONTHLY("premiumMonthly"),
    PREMIUM_YEARLY("premiumYearly");

    val id: String
        get() = rawValue

    val displayName: String
        get() = when (this) {
            FREE -> "Free"
            PREMIUM_MONTHLY -> "Premium Monthly"
            PREMIUM_YEARLY -> "Premium Yearly"
        }

    val productID: String?
        get() = when (this) {
            FREE -> null
            PREMIUM_MONTHLY -> "com.tracewave.choghadiya.premium.monthly"
            PREMIUM_YEARLY -> "com.tracewave.choghadiya.premium.yearly"
        }

    val fallbackPrice: String
        get() = when (this) {
            FREE -> "Free"
            PREMIUM_MONTHLY -> "₹29 / month"
            PREMIUM_YEARLY -> "₹249 / year"
        }

    val sortPriority: Int
        get() = when (this) {
            PREMIUM_YEARLY -> 2
            PREMIUM_MONTHLY -> 1
            FREE -> 0
        }

    val isPremium: Boolean
        get() = this != FREE

    companion object {
        val premiumCases: List<SubscriptionPlan>
            get() = listOf(PREMIUM_YEARLY, PREMIUM_MONTHLY)

        fun fromRawValue(value: String): SubscriptionPlan {
            return entries.firstOrNull { it.rawValue == value } ?: FREE
        }

        fun fromProductID(productID: String): SubscriptionPlan {
            return premiumCases.firstOrNull { it.productID == productID } ?: FREE
        }
    }
}

enum class LocationMode {
    MANUAL,
    CURRENT;

    val rawValue: String
        get() = name.lowercase()

    companion object {
        fun fromRawValue(value: String): LocationMode {
            return if (value.lowercase() == "current") CURRENT else MANUAL
        }
    }
}
