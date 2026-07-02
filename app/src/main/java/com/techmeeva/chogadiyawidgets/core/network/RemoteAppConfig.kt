package com.techmeeva.chogadiyawidgets.core.network

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RemoteAppConfig(
    @SerializedName("apiBaseURL") val apiBaseURL: String? = null,
    @SerializedName("appStoreURL") val appStoreURL: String? = null,
    @SerializedName("playStoreURL") val playStoreURL: String? = null,
    @SerializedName("minimumSupportedVersion") val minimumSupportedVersion: String = "1.0",
    @SerializedName("latestVersion") val latestVersion: String = "1.0",
    @SerializedName("forceUpdate") val forceUpdate: Boolean = false,
    @SerializedName("updateTitle") val updateTitle: String = "Update available",
    @SerializedName("updateMessage") val updateMessage: String = "A newer version of Shubh Muhurat is available with improvements.",
    @SerializedName("rating") val rating: RatingPromptConfig = RatingPromptConfig(),
    @SerializedName("notifications") val notifications: NotificationPromptConfig = NotificationPromptConfig(),
    @SerializedName("engagement") val engagement: EngagementPromptConfig = EngagementPromptConfig()
)

@Keep
data class RatingPromptConfig(
    @SerializedName("enabled") val enabled: Boolean = true,
    @SerializedName("minOpenCount") val minOpenCount: Int = 3,
    @SerializedName("minDaysSinceFirstOpen") val minDaysSinceFirstOpen: Int = 0,
    @SerializedName("cooldownDays") val cooldownDays: Int = 45
)

@Keep
data class NotificationPromptConfig(
    @SerializedName("enabled") val enabled: Boolean = true,
    @SerializedName("minOpenCount") val minOpenCount: Int = 3,
    @SerializedName("delaySecondsAfterOpen") val delaySecondsAfterOpen: Double = 2.0,
    @SerializedName("cooldownDays") val cooldownDays: Int = 21,
    @SerializedName("title") val title: String = "Daily Choghadiya reminders",
    @SerializedName("message") val message: String = "Get gentle reminders for auspicious timings and daily rhythm."
)

@Keep
data class EngagementPromptConfig(
    @SerializedName("enabled") val enabled: Boolean = true,
    @SerializedName("milestoneDays") val milestoneDays: List<Int> = listOf(7, 30, 100),
    @SerializedName("title") val title: String = "A beautiful streak",
    @SerializedName("message") val message: String = "You have been aligning your days with Shubh Muhurat."
)
