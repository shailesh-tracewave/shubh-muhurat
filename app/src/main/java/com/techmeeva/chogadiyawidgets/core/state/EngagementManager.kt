package com.techmeeva.chogadiyawidgets.core.state

import android.content.Context
import android.content.SharedPreferences
import com.techmeeva.chogadiyawidgets.core.network.EngagementPromptConfig
import com.techmeeva.chogadiyawidgets.core.network.NotificationPromptConfig
import com.techmeeva.chogadiyawidgets.core.network.RatingPromptConfig
import com.techmeeva.chogadiyawidgets.core.network.RemoteAppConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

class EngagementManager(private val context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("engagement_prefs", Context.MODE_PRIVATE)

    private val _surface = MutableStateFlow<EngagementSurface?>(null)
    val surface: StateFlow<EngagementSurface?> = _surface

    companion object {
        private const val FIRST_OPEN_KEY = "engagement.firstOpenDate"
        private const val OPEN_COUNT_KEY = "engagement.openCount"
        private const val LAST_RATING_PROMPT_KEY = "engagement.lastRatingPrompt"
        private const val LAST_NOTIFICATION_PROMPT_KEY = "engagement.lastNotificationPrompt"
        private const val SKIPPED_UPDATE_VERSION_KEY = "engagement.skippedUpdateVersion"
        private const val SHOWN_MILESTONES_KEY = "engagement.shownMilestones"
    }

    fun recordLaunch() {
        if (!prefs.contains(FIRST_OPEN_KEY)) {
            prefs.edit().putLong(FIRST_OPEN_KEY, System.currentTimeMillis()).apply()
        }
        val count = prefs.getInt(OPEN_COUNT_KEY, 0)
        prefs.edit().putInt(OPEN_COUNT_KEY, count + 1).apply()
    }

    fun evaluate(config: RemoteAppConfig) {
        val updateSurface = updateSurface(config)
        if (updateSurface != null) {
            _surface.value = updateSurface
            return
        }

        val milestone = milestoneSurface(config.engagement)
        if (milestone != null) {
            _surface.value = milestone
            return
        }

        if (shouldShowRating(config.rating)) {
            _surface.value = EngagementSurface.Rating
            return
        }

        if (shouldShowNotification(config.notifications)) {
            val delaySeconds = config.notifications.delaySecondsAfterOpen
            CoroutineScope(Dispatchers.Main).launch {
                delay((delaySeconds * 1000).toLong().coerceAtLeast(0))
                if (_surface.value == null) {
                    _surface.value = EngagementSurface.Notification(
                        title = config.notifications.title,
                        message = config.notifications.message
                    )
                }
            }
        }
    }

    fun dismiss() {
        _surface.value = null
    }

    fun skipUpdate(version: String) {
        prefs.edit().putString(SKIPPED_UPDATE_VERSION_KEY, version).apply()
        dismiss()
    }

    fun markRatingPrompted() {
        prefs.edit().putLong(LAST_RATING_PROMPT_KEY, System.currentTimeMillis()).apply()
        dismiss()
    }

    fun markNotificationPrompted() {
        prefs.edit().putLong(LAST_NOTIFICATION_PROMPT_KEY, System.currentTimeMillis()).apply()
        dismiss()
    }

    fun markMilestoneShown(days: Int) {
        val shown = prefs.getStringSet(SHOWN_MILESTONES_KEY, emptySet())?.toMutableSet() ?: mutableSetOf()
        shown.add(days.toString())
        prefs.edit().putStringSet(SHOWN_MILESTONES_KEY, shown).apply()
        dismiss()
    }

    private fun updateSurface(config: RemoteAppConfig): EngagementSurface? {
        val currentVersion = try {
            context.packageManager.getPackageInfo(context.packageName, 0).versionName ?: "1.0.0"
        } catch (e: Exception) {
            "1.0.0"
        }

        if (isVersionOlderThan(currentVersion, config.minimumSupportedVersion) || config.forceUpdate) {
            return EngagementSurface.ForceUpdate(config.updateTitle, config.updateMessage, config.appStoreURL)
        }

        val skippedVersion = prefs.getString(SKIPPED_UPDATE_VERSION_KEY, null)
        if (isVersionOlderThan(currentVersion, config.latestVersion) && skippedVersion != config.latestVersion) {
            return EngagementSurface.OptionalUpdate(config.updateTitle, config.updateMessage, config.appStoreURL, config.latestVersion)
        }

        return null
    }

    private fun milestoneSurface(config: EngagementPromptConfig): EngagementSurface? {
        if (!config.enabled || !prefs.contains(FIRST_OPEN_KEY)) return null

        val firstOpenTime = prefs.getLong(FIRST_OPEN_KEY, 0)
        val elapsedDays = getDaysBetween(Date(firstOpenTime), Date())
        val shown = prefs.getStringSet(SHOWN_MILESTONES_KEY, emptySet()) ?: emptySet()

        val validMilestones = config.milestoneDays.sorted()
        val milestone = validMilestones.lastOrNull { elapsedDays >= it && !shown.contains(it.toString()) }

        if (milestone != null) {
            return EngagementSurface.Milestone(milestone, config.title, config.message)
        }
        return null
    }

    private fun shouldShowRating(config: RatingPromptConfig): Boolean {
        if (!config.enabled) return false
        val openCount = prefs.getInt(OPEN_COUNT_KEY, 0)
        if (openCount < config.minOpenCount) return false

        val firstOpenTime = prefs.getLong(FIRST_OPEN_KEY, 0)
        val daysSinceFirstOpen = if (firstOpenTime > 0) getDaysBetween(Date(firstOpenTime), Date()) else 0
        if (daysSinceFirstOpen < config.minDaysSinceFirstOpen) return false

        val lastPrompt = prefs.getLong(LAST_RATING_PROMPT_KEY, 0)
        val daysSinceLastPrompt = if (lastPrompt > 0) getDaysBetween(Date(lastPrompt), Date()) else Int.MAX_VALUE
        
        return daysSinceLastPrompt >= config.cooldownDays
    }

    private fun shouldShowNotification(config: NotificationPromptConfig): Boolean {
        if (!config.enabled) return false
        val openCount = prefs.getInt(OPEN_COUNT_KEY, 0)
        if (openCount < config.minOpenCount) return false

        val lastPrompt = prefs.getLong(LAST_NOTIFICATION_PROMPT_KEY, 0)
        val daysSinceLastPrompt = if (lastPrompt > 0) getDaysBetween(Date(lastPrompt), Date()) else Int.MAX_VALUE
        
        return daysSinceLastPrompt >= config.cooldownDays
    }

    private fun getDaysBetween(start: Date, end: Date): Int {
        val diff = end.time - start.time
        return (diff / (1000 * 60 * 60 * 24)).toInt()
    }

    private fun isVersionOlderThan(v1: String, v2: String): Boolean {
        val parts1 = v1.split(".").map { it.toIntOrNull() ?: 0 }
        val parts2 = v2.split(".").map { it.toIntOrNull() ?: 0 }
        val maxLen = maxOf(parts1.size, parts2.size)
        for (i in 0 until maxLen) {
            val p1 = parts1.getOrElse(i) { 0 }
            val p2 = parts2.getOrElse(i) { 0 }
            if (p1 < p2) return true
            if (p1 > p2) return false
        }
        return false
    }
}
