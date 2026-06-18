package com.techmeeva.chogadiyawidgets.widget

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.SizeF
import android.widget.RemoteViews
import androidx.core.content.ContextCompat
import com.techmeeva.chogadiyawidgets.R
import com.techmeeva.chogadiyawidgets.core.localization.AppLanguage
import com.techmeeva.chogadiyawidgets.core.localization.AppLocalizer
import com.techmeeva.chogadiyawidgets.core.localization.AppTextKey
import com.techmeeva.chogadiyawidgets.core.state.HomeTimelineState
import java.util.Locale

object WidgetResponsiveBuilder {

    fun build(
        context: Context,
        state: HomeTimelineState,
        astroDay: com.techmeeva.chogadiyawidgets.models.SolarLunarDay?,
        language: AppLanguage,
        timezone: String
    ): RemoteViews {
        val smallView = buildSmallView(context, state, language, timezone)
        val mediumView = buildMediumView(context, state, astroDay, language, timezone)
        val largeView = buildLargeView(context, state, astroDay, language, timezone)

        // API 31+ Responsive Views
        val viewMapping = mapOf(
            SizeF(100f, 100f) to smallView,    // 2x2
            SizeF(220f, 130f) to mediumView,   // 4x2
            SizeF(220f, 280f) to largeView     // 4x4
        )
        return RemoteViews(viewMapping)
    }

    fun buildPlaceholder(context: Context): RemoteViews {
        val small = buildPlaceholderView(context, R.layout.widget_timeline)
        val medium = buildPlaceholderView(context, R.layout.widget_detail_card)
        val large = buildPlaceholderView(context, R.layout.widget_large)
        
        val viewMapping = mapOf(
            SizeF(100f, 100f) to small,
            SizeF(220f, 130f) to medium,
            SizeF(220f, 280f) to large
        )
        return RemoteViews(viewMapping)
    }

    private fun buildPlaceholderView(context: Context, layoutId: Int): RemoteViews {
        val views = RemoteViews(context.packageName, layoutId)
        val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
        if (intent != null) {
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            // Depending on the layout, find a top-level interactable element or just the whole widget
            // All widgets have a "current" or "top" label that works for launching
            if (layoutId == R.layout.widget_timeline) {
                views.setOnClickPendingIntent(R.id.widget_label_current, pendingIntent)
                views.setTextViewText(R.id.widget_active_name, "Loading...")
                views.setTextViewText(R.id.widget_countdown, "--:--")
                views.setTextViewText(R.id.widget_active_range, "")
                views.setProgressBar(R.id.widget_progress, 100, 0, false)
            } else if (layoutId == R.layout.widget_detail_card) {
                views.setOnClickPendingIntent(R.id.widget_detail_label_current, pendingIntent)
                views.setTextViewText(R.id.widget_detail_active_name, "Loading...")
                views.setTextViewText(R.id.widget_detail_countdown, "--:--")
                views.setTextViewText(R.id.widget_detail_active_range, "")
                views.setProgressBar(R.id.widget_detail_progress, 100, 0, false)
            } else if (layoutId == R.layout.widget_large) {
                views.setOnClickPendingIntent(R.id.widget_large_label_current, pendingIntent)
                views.setTextViewText(R.id.widget_large_active_name, "Loading...")
                views.setTextViewText(R.id.widget_large_active_meaning, "")
                views.setTextViewText(R.id.widget_large_countdown, "--:--")
                views.setTextViewText(R.id.widget_large_active_range, "")
                views.setProgressBar(R.id.widget_large_progress, 100, 0, false)
            }
        }
        return views
    }

    private fun getLaunchIntent(context: Context): PendingIntent? {
        val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
        return if (intent != null) {
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        } else null
    }

    private fun buildSmallView(
        context: Context,
        state: HomeTimelineState,
        language: AppLanguage,
        timezone: String
    ): RemoteViews {
        val views = RemoteViews(context.packageName, R.layout.widget_timeline)
        val pendingIntent = getLaunchIntent(context)
        if (pendingIntent != null) views.setOnClickPendingIntent(R.id.widget_label_current, pendingIntent)

        val currentSlot = state.currentSlot
        val upcoming = state.nextSlots.take(3)

        views.setTextViewText(R.id.widget_label_current, AppLocalizer.text(AppTextKey.CURRENT_SLOT, language))
        views.setTextViewText(R.id.widget_label_left, AppLocalizer.text(AppTextKey.REMAINING, language))

        if (currentSlot != null) {
            views.setTextViewText(R.id.widget_active_name, AppLocalizer.slotName(currentSlot.type, language))
            views.setTextColor(R.id.widget_active_name, ContextCompat.getColor(context, R.color.celestial_text))
            
            views.setTextViewText(R.id.widget_active_meaning, AppLocalizer.slotMeaning(currentSlot.type, language))
            
            val rangeStr = AppLocalizer.localizedRange(currentSlot.start, currentSlot.end, language, timezone)
            views.setTextViewText(R.id.widget_active_range, rangeStr)
            
            views.setInt(R.id.widget_dot, "setColorFilter", ContextCompat.getColor(context, getSlotColorRes(currentSlot.type)))
            views.setTextColor(R.id.widget_countdown, ContextCompat.getColor(context, getSlotColorRes(currentSlot.type)))

            val now = System.currentTimeMillis()
            val diffSeconds = (currentSlot.end.time - now) / 1000
            val totalSeconds = (currentSlot.end.time - currentSlot.start.time) / 1000
            val progressPercent = if (totalSeconds > 0) {
                ((totalSeconds - diffSeconds).toFloat() / totalSeconds.toFloat() * 100).toInt()
            } else 0

            if (diffSeconds > 0) {
                val hours = diffSeconds / 3600
                val minutes = (diffSeconds % 3600) / 60
                views.setTextViewText(R.id.widget_countdown, String.format(Locale.US, "%02d:%02d", hours, minutes))
            } else {
                views.setTextViewText(R.id.widget_countdown, "00:00")
            }

            views.setProgressBar(R.id.widget_progress, 100, progressPercent, false)

            if (upcoming.isNotEmpty()) {
                views.setTextViewText(R.id.widget_next_1_name, AppLocalizer.slotName(upcoming[0].type, language))
                views.setTextViewText(R.id.widget_next_1_time, AppLocalizer.localizedTime(upcoming[0].start, language, timezone))
                views.setInt(R.id.widget_next_1_dot, "setColorFilter", ContextCompat.getColor(context, getSlotColorRes(upcoming[0].type)))
            }
            if (upcoming.size >= 2) {
                views.setTextViewText(R.id.widget_next_2_name, AppLocalizer.slotName(upcoming[1].type, language))
                views.setTextViewText(R.id.widget_next_2_time, AppLocalizer.localizedTime(upcoming[1].start, language, timezone))
                views.setInt(R.id.widget_next_2_dot, "setColorFilter", ContextCompat.getColor(context, getSlotColorRes(upcoming[1].type)))
            }
            if (upcoming.size >= 3) {
                views.setTextViewText(R.id.widget_next_3_name, AppLocalizer.slotName(upcoming[2].type, language))
                views.setTextViewText(R.id.widget_next_3_time, AppLocalizer.localizedTime(upcoming[2].start, language, timezone))
                views.setInt(R.id.widget_next_3_dot, "setColorFilter", ContextCompat.getColor(context, getSlotColorRes(upcoming[2].type)))
            }
        } else {
            views.setTextViewText(R.id.widget_active_name, "Loading...")
            views.setTextViewText(R.id.widget_countdown, "00:00")
            views.setProgressBar(R.id.widget_progress, 100, 0, false)
        }
        return views
    }

    private fun buildMediumView(
        context: Context,
        state: HomeTimelineState,
        astroDay: com.techmeeva.chogadiyawidgets.models.SolarLunarDay?,
        language: AppLanguage,
        timezone: String
    ): RemoteViews {
        val views = RemoteViews(context.packageName, R.layout.widget_detail_card)
        val pendingIntent = getLaunchIntent(context)
        if (pendingIntent != null) views.setOnClickPendingIntent(R.id.widget_detail_label_current, pendingIntent)

        val currentSlot = state.currentSlot
        val upcoming = state.nextSlots.take(3)

        views.setTextViewText(R.id.widget_detail_label_current, AppLocalizer.text(AppTextKey.CURRENT_SLOT, language))
        views.setTextViewText(R.id.widget_detail_label_left, AppLocalizer.text(AppTextKey.REMAINING, language))

        if (currentSlot != null) {
            views.setTextViewText(R.id.widget_detail_active_name, AppLocalizer.slotName(currentSlot.type, language))
            views.setTextColor(R.id.widget_detail_active_name, ContextCompat.getColor(context, R.color.celestial_text))
            
            views.setTextViewText(R.id.widget_detail_active_meaning, AppLocalizer.slotMeaning(currentSlot.type, language))
            
            val rangeStr = AppLocalizer.localizedRange(currentSlot.start, currentSlot.end, language, timezone)
            views.setTextViewText(R.id.widget_detail_active_range, rangeStr)
            
            views.setInt(R.id.widget_detail_dot, "setColorFilter", ContextCompat.getColor(context, getSlotColorRes(currentSlot.type)))
            views.setTextColor(R.id.widget_detail_countdown, ContextCompat.getColor(context, getSlotColorRes(currentSlot.type)))

            val now = System.currentTimeMillis()
            val diffSeconds = (currentSlot.end.time - now) / 1000
            val totalSeconds = (currentSlot.end.time - currentSlot.start.time) / 1000
            val progressPercent = if (totalSeconds > 0) {
                ((totalSeconds - diffSeconds).toFloat() / totalSeconds.toFloat() * 100).toInt()
            } else 0

            if (diffSeconds > 0) {
                val hours = diffSeconds / 3600
                val minutes = (diffSeconds % 3600) / 60
                views.setTextViewText(R.id.widget_detail_countdown, String.format(Locale.US, "%02d:%02d", hours, minutes))
            } else {
                views.setTextViewText(R.id.widget_detail_countdown, "00:00")
            }
            views.setProgressBar(R.id.widget_detail_progress, 100, progressPercent, false)

            if (upcoming.isNotEmpty()) {
                views.setTextViewText(R.id.widget_detail_next_1_name, AppLocalizer.slotName(upcoming[0].type, language))
                views.setTextViewText(R.id.widget_detail_next_1_time, AppLocalizer.localizedTime(upcoming[0].start, language, timezone))
                views.setInt(R.id.widget_detail_next_1_dot, "setColorFilter", ContextCompat.getColor(context, getSlotColorRes(upcoming[0].type)))
            }
            if (upcoming.size >= 2) {
                views.setTextViewText(R.id.widget_detail_next_2_name, AppLocalizer.slotName(upcoming[1].type, language))
                views.setTextViewText(R.id.widget_detail_next_2_time, AppLocalizer.localizedTime(upcoming[1].start, language, timezone))
                views.setInt(R.id.widget_detail_next_2_dot, "setColorFilter", ContextCompat.getColor(context, getSlotColorRes(upcoming[1].type)))
            }
            if (upcoming.size >= 3) {
                views.setTextViewText(R.id.widget_detail_next_3_name, AppLocalizer.slotName(upcoming[2].type, language))
                views.setTextViewText(R.id.widget_detail_next_3_time, AppLocalizer.localizedTime(upcoming[2].start, language, timezone))
                views.setInt(R.id.widget_detail_next_3_dot, "setColorFilter", ContextCompat.getColor(context, getSlotColorRes(upcoming[2].type)))
            }
        }

        if (astroDay != null) {
            views.setTextViewText(R.id.widget_metric_sunrise_time, AppLocalizer.localizedTime(astroDay.sunrise, language))
            views.setTextViewText(R.id.widget_metric_sunset_time, AppLocalizer.localizedTime(astroDay.sunset, language))
            views.setTextViewText(R.id.widget_metric_moonrise_time, astroDay.moonrise?.let { AppLocalizer.localizedTime(it, language) } ?: "--")
            views.setTextViewText(R.id.widget_metric_moonset_time, astroDay.moonset?.let { AppLocalizer.localizedTime(it, language) } ?: "--")
            views.setTextViewText(R.id.widget_metric_noon_time, AppLocalizer.localizedTime(astroDay.solarNoon, language))
            
            val phaseVal = (astroDay.illuminationFraction * 100).toInt()
            views.setTextViewText(R.id.widget_metric_illumination_value, "$phaseVal%")
        }

        return views
    }

    private fun buildLargeView(
        context: Context,
        state: HomeTimelineState,
        astroDay: com.techmeeva.chogadiyawidgets.models.SolarLunarDay?,
        language: AppLanguage,
        timezone: String
    ): RemoteViews {
        val views = RemoteViews(context.packageName, R.layout.widget_large)
        val pendingIntent = getLaunchIntent(context)
        if (pendingIntent != null) views.setOnClickPendingIntent(R.id.widget_large_label_current, pendingIntent)

        val currentSlot = state.currentSlot
        val upcoming = state.nextSlots.take(3)

        views.setTextViewText(R.id.widget_large_label_current, AppLocalizer.text(AppTextKey.CURRENT_SLOT, language))

        if (currentSlot != null) {
            val name = AppLocalizer.slotName(currentSlot.type, language)
            views.setTextViewText(R.id.widget_large_active_name, name)
            views.setTextColor(R.id.widget_large_active_name, ContextCompat.getColor(context, R.color.celestial_text))
            
            views.setTextViewText(R.id.widget_large_active_meaning, AppLocalizer.slotMeaning(currentSlot.type, language))
            
            val rangeStr = AppLocalizer.localizedRange(currentSlot.start, currentSlot.end, language, timezone)
            views.setTextViewText(R.id.widget_large_active_range, rangeStr)
            
            views.setInt(R.id.widget_large_dot, "setColorFilter", ContextCompat.getColor(context, getSlotColorRes(currentSlot.type)))
            views.setTextColor(R.id.widget_large_countdown, ContextCompat.getColor(context, getSlotColorRes(currentSlot.type)))

            val now = System.currentTimeMillis()
            val diffSeconds = (currentSlot.end.time - now) / 1000
            val totalSeconds = (currentSlot.end.time - currentSlot.start.time) / 1000
            val progressPercent = if (totalSeconds > 0) {
                ((totalSeconds - diffSeconds).toFloat() / totalSeconds.toFloat() * 100).toInt()
            } else 0

            if (diffSeconds > 0) {
                val hours = diffSeconds / 3600
                val minutes = (diffSeconds % 3600) / 60
                views.setTextViewText(R.id.widget_large_countdown, String.format(Locale.US, "%02d:%02d", hours, minutes))
            } else {
                views.setTextViewText(R.id.widget_large_countdown, "00:00")
            }
            views.setProgressBar(R.id.widget_large_progress, 100, progressPercent, false)

            if (upcoming.isNotEmpty()) {
                views.setTextViewText(R.id.widget_large_next_1_name, AppLocalizer.slotName(upcoming[0].type, language))
                views.setTextViewText(R.id.widget_large_next_1_time, AppLocalizer.localizedTime(upcoming[0].start, language, timezone))
                views.setInt(R.id.widget_large_next_1_dot, "setColorFilter", ContextCompat.getColor(context, getSlotColorRes(upcoming[0].type)))
            }
            if (upcoming.size >= 2) {
                views.setTextViewText(R.id.widget_large_next_2_name, AppLocalizer.slotName(upcoming[1].type, language))
                views.setTextViewText(R.id.widget_large_next_2_time, AppLocalizer.localizedTime(upcoming[1].start, language, timezone))
                views.setInt(R.id.widget_large_next_2_dot, "setColorFilter", ContextCompat.getColor(context, getSlotColorRes(upcoming[1].type)))
            }
            if (upcoming.size >= 3) {
                views.setTextViewText(R.id.widget_large_next_3_name, AppLocalizer.slotName(upcoming[2].type, language))
                views.setTextViewText(R.id.widget_large_next_3_time, AppLocalizer.localizedTime(upcoming[2].start, language, timezone))
                views.setInt(R.id.widget_large_next_3_dot, "setColorFilter", ContextCompat.getColor(context, getSlotColorRes(upcoming[2].type)))
            }
        }

        if (astroDay != null) {
            views.setTextViewText(R.id.widget_large_metric_sunrise, AppLocalizer.localizedTime(astroDay.sunrise, language))
            views.setTextViewText(R.id.widget_large_metric_sunset, AppLocalizer.localizedTime(astroDay.sunset, language))
            views.setTextViewText(R.id.widget_large_metric_moonrise, astroDay.moonrise?.let { AppLocalizer.localizedTime(it, language) } ?: "--")
            views.setTextViewText(R.id.widget_large_metric_moonset, astroDay.moonset?.let { AppLocalizer.localizedTime(it, language) } ?: "--")
            views.setTextViewText(R.id.widget_large_metric_noon, AppLocalizer.localizedTime(astroDay.solarNoon, language))
            
            val phaseVal = (astroDay.illuminationFraction * 100).toInt()
            views.setTextViewText(R.id.widget_large_metric_phase, "$phaseVal%")
            
            val auspicious = astroDay.auspiciousWindows
            if (auspicious.isNotEmpty()) {
                views.setTextViewText(R.id.widget_large_auspicious_1_name, AppLocalizer.astronomyWindowTitle(auspicious[0].key, language))
                val t1 = "${AppLocalizer.localizedTime(auspicious[0].startTime, language)} - ${AppLocalizer.localizedTime(auspicious[0].endTime, language)}"
                views.setTextViewText(R.id.widget_large_auspicious_1_time, t1)
            }
            if (auspicious.size > 1) {
                views.setTextViewText(R.id.widget_large_auspicious_2_name, AppLocalizer.astronomyWindowTitle(auspicious[1].key, language))
                val t2 = "${AppLocalizer.localizedTime(auspicious[1].startTime, language)} - ${AppLocalizer.localizedTime(auspicious[1].endTime, language)}"
                views.setTextViewText(R.id.widget_large_auspicious_2_time, t2)
            }

            val inauspicious = astroDay.inauspiciousWindows
            if (inauspicious.isNotEmpty()) {
                views.setTextViewText(R.id.widget_large_inauspicious_1_name, AppLocalizer.astronomyWindowTitle(inauspicious[0].key, language))
                val t3 = "${AppLocalizer.localizedTime(inauspicious[0].startTime, language)} - ${AppLocalizer.localizedTime(inauspicious[0].endTime, language)}"
                views.setTextViewText(R.id.widget_large_inauspicious_1_time, t3)
            }
            if (inauspicious.size > 1) {
                views.setTextViewText(R.id.widget_large_inauspicious_2_name, AppLocalizer.astronomyWindowTitle(inauspicious[1].key, language))
                val t4 = "${AppLocalizer.localizedTime(inauspicious[1].startTime, language)} - ${AppLocalizer.localizedTime(inauspicious[1].endTime, language)}"
                views.setTextViewText(R.id.widget_large_inauspicious_2_time, t4)
            }
        }

        return views
    }

    private fun getSlotColorRes(type: String): Int {
        return when (type.lowercase(Locale.US)) {
            "shubh" -> R.color.choghadiya_shubh
            "labh" -> R.color.choghadiya_labh
            "amrit" -> R.color.choghadiya_amrit
            "chal" -> R.color.choghadiya_chal
            "rog" -> R.color.choghadiya_rog
            "kal" -> R.color.choghadiya_kal
            "udveg" -> R.color.choghadiya_udveg
            else -> R.color.celestial_text_muted
        }
    }
}
