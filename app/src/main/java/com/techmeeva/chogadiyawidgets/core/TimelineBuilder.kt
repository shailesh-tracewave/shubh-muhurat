package com.techmeeva.chogadiyawidgets.core

import com.techmeeva.chogadiyawidgets.models.ChoghadiyaDay
import com.techmeeva.chogadiyawidgets.models.TimelineSlot
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object TimelineBuilder {

    fun buildTimeline(days: List<ChoghadiyaDay>, timezone: String): List<TimelineSlot> {
        val slots = mutableListOf<TimelineSlot>()
        for (day in days) {
            for (slot in day.slots) {
                val start = dateFrom(day.date, slot.startSeconds, timezone)
                val end = dateFrom(day.date, slot.endSeconds, timezone)
                if (start != null && end != null) {
                    slots.add(
                        TimelineSlot(
                            id = "${day.date}-${slot.slot}-${slot.startSeconds}",
                            dayDate = day.date,
                            type = slot.type,
                            start = start,
                            end = end,
                            phase = slot.phase,
                            slotNumber = slot.slot
                        )
                    )
                }
            }
        }
        return slots.sortedBy { it.start }
    }

    fun visibleTimeline(days: List<ChoghadiyaDay>, timezone: String, now: Date): List<TimelineSlot> {
        val timeline = buildTimeline(days, timezone)
        if (timeline.isEmpty()) return emptyList()

        val currentIndex = timeline.indexOfFirst { it.start.time <= now.time && now.time < it.end.time }
        if (currentIndex != -1) {
            return timeline.drop(currentIndex).take(7)
        }

        val upcomingIndex = timeline.indexOfFirst { it.start.time > now.time }
        if (upcomingIndex != -1) {
            return timeline.drop(upcomingIndex).take(7)
        }

        return timeline.takeLast(7)
    }

    fun displayName(type: String): String {
        return when (type.lowercase(Locale.US)) {
            "shubh" -> "Shubh"
            "labh" -> "Labh"
            "amrit" -> "Amrit"
            "chal" -> "Char"
            "rog" -> "Rog"
            "kal" -> "Kaal"
            "udveg" -> "Udveg"
            else -> type.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.US) else it.toString() }
        }
    }

    private fun dateFrom(day: String, seconds: Int, timezone: String): Date? {
        return try {
            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US).apply {
                timeZone = TimeZone.getTimeZone(timezone)
            }
            val baseDate = formatter.parse(day) ?: return null
            val calendar = Calendar.getInstance(TimeZone.getTimeZone(timezone), Locale.US).apply {
                time = baseDate
                add(Calendar.SECOND, seconds)
            }
            calendar.time
        } catch (e: Exception) {
            null
        }
    }
}
