package com.techmeeva.chogadiyawidgets.core.state

sealed class EngagementSurface {
    data class ForceUpdate(val title: String, val message: String, val appStoreURL: String?) : EngagementSurface()
    data class OptionalUpdate(val title: String, val message: String, val appStoreURL: String?, val targetVersion: String) : EngagementSurface()
    object Rating : EngagementSurface()
    data class Notification(val title: String, val message: String) : EngagementSurface()
    data class Milestone(val days: Int, val title: String, val message: String) : EngagementSurface()
}
