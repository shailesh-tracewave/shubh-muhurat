package com.techmeeva.chogadiyawidgets.widget

import android.app.PendingIntent
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.os.SystemClock
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.util.SizeF
import android.view.View
import android.widget.RemoteViews
import androidx.core.content.ContextCompat
import com.techmeeva.chogadiyawidgets.R
import com.techmeeva.chogadiyawidgets.core.localization.AppLanguage
import com.techmeeva.chogadiyawidgets.core.localization.AppLocalizer
import com.techmeeva.chogadiyawidgets.core.localization.AppThemeMode
import com.techmeeva.chogadiyawidgets.core.localization.LocalizedContentLanguage
import com.techmeeva.chogadiyawidgets.core.state.HomeTimelineState
import com.techmeeva.chogadiyawidgets.core.state.AppState
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
            SizeF(220f, 300f) to largeView     // 4x4 / tall resized widgets
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
            SizeF(220f, 300f) to large
        )
        return RemoteViews(viewMapping)
    }

    private fun buildPlaceholderView(context: Context, layoutId: Int): RemoteViews {
        val views = RemoteViews(context.packageName, layoutId)
        val theme = WidgetTheme.from(context)
        when (layoutId) {
            R.layout.widget_timeline -> applySmallTheme(views, theme)
            R.layout.widget_detail_card -> applyMediumTheme(views, theme)
            R.layout.widget_large -> applyLargeTheme(views, theme)
        }
        val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
        if (intent != null) {
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            // Depending on the layout, find a top-level interactable element or just the whole widget
            // All widgets have a "current" or "top" label that works for launching
            if (layoutId == R.layout.widget_timeline) {
                views.setOnClickPendingIntent(R.id.widget_root, pendingIntent)
                views.setOnClickPendingIntent(R.id.widget_label_current, pendingIntent)
                views.setTextViewText(R.id.widget_active_name, "Loading...")
                views.setTextViewText(R.id.widget_active_meaning, "")
                stopCountdown(views, R.id.widget_countdown, "--:--")
                views.setTextViewText(R.id.widget_active_range, "")
                views.setProgressBar(R.id.widget_progress, 100, 0, false)
            } else if (layoutId == R.layout.widget_detail_card) {
                views.setOnClickPendingIntent(R.id.widget_detail_root, pendingIntent)
                views.setOnClickPendingIntent(R.id.widget_detail_label_current, pendingIntent)
                views.setTextViewText(R.id.widget_detail_active_name, "Loading...")
                views.setTextViewText(R.id.widget_detail_active_meaning, "")
                stopCountdown(views, R.id.widget_detail_countdown, "--:--")
                views.setTextViewText(R.id.widget_detail_active_range, "")
                views.setProgressBar(R.id.widget_detail_progress, 100, 0, false)
                clearMediumMetrics(views)
                clearMediumUpcoming(views)
            } else if (layoutId == R.layout.widget_large) {
                views.setOnClickPendingIntent(R.id.widget_large_root, pendingIntent)
                views.setOnClickPendingIntent(R.id.widget_large_label_current, pendingIntent)
                views.setTextViewText(R.id.widget_large_active_name, "Loading...")
                views.setTextViewText(R.id.widget_large_active_meaning, "")
                stopCountdown(views, R.id.widget_large_countdown, "--:--")
                views.setTextViewText(R.id.widget_large_active_range, "")
                views.setProgressBar(R.id.widget_large_progress, 100, 0, false)
                clearLargeMetrics(views)
                clearLargeUpcoming(views)
                clearLargeWindows(views)
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
        val theme = WidgetTheme.from(context)
        applySmallTheme(views, theme)
        val pendingIntent = getLaunchIntent(context)
        if (pendingIntent != null) {
            views.setOnClickPendingIntent(R.id.widget_root, pendingIntent)
            views.setOnClickPendingIntent(R.id.widget_label_current, pendingIntent)
        }

        val currentSlot = state.currentSlot
        val upcoming = state.nextSlots.take(3)
        clearSmallUpcoming(views)

        views.setTextViewText(R.id.widget_label_current, currentShort(language))
        views.setTextViewText(R.id.widget_label_left, remainingShort(language))

        if (currentSlot != null) {
            views.setTextViewText(R.id.widget_active_name, AppLocalizer.slotName(currentSlot.type, language))
            
            views.setTextViewText(R.id.widget_active_meaning, AppLocalizer.slotMeaning(currentSlot.type, language))
            
            val rangeStr = AppLocalizer.localizedRange(currentSlot.start, currentSlot.end, language, timezone)
            views.setTextViewText(R.id.widget_active_range, rangeStr)
            
            views.setInt(R.id.widget_dot, "setColorFilter", slotColor(theme, currentSlot.type))
            views.setTextColor(R.id.widget_countdown, slotColor(theme, currentSlot.type))

            val now = System.currentTimeMillis()
            val diffSeconds = (currentSlot.end.time - now) / 1000
            val totalSeconds = (currentSlot.end.time - currentSlot.start.time) / 1000
            val progressPercent = if (totalSeconds > 0) {
                ((totalSeconds - diffSeconds).toFloat() / totalSeconds.toFloat() * 100).toInt()
            } else 0

            bindCountdown(views, R.id.widget_countdown, currentSlot.end.time)

            views.setProgressBar(R.id.widget_progress, 100, progressPercent.coerceIn(0, 100), false)

            if (upcoming.isNotEmpty()) {
                views.setTextViewText(R.id.widget_next_1_name, AppLocalizer.slotName(upcoming[0].type, language))
                views.setTextViewText(R.id.widget_next_1_time, AppLocalizer.localizedTime(upcoming[0].start, language, timezone))
                views.setInt(R.id.widget_next_1_dot, "setColorFilter", slotColor(theme, upcoming[0].type))
            }
            if (upcoming.size >= 2) {
                views.setTextViewText(R.id.widget_next_2_name, AppLocalizer.slotName(upcoming[1].type, language))
                views.setTextViewText(R.id.widget_next_2_time, AppLocalizer.localizedTime(upcoming[1].start, language, timezone))
                views.setInt(R.id.widget_next_2_dot, "setColorFilter", slotColor(theme, upcoming[1].type))
            }
            if (upcoming.size >= 3) {
                views.setTextViewText(R.id.widget_next_3_name, AppLocalizer.slotName(upcoming[2].type, language))
                views.setTextViewText(R.id.widget_next_3_time, AppLocalizer.localizedTime(upcoming[2].start, language, timezone))
                views.setInt(R.id.widget_next_3_dot, "setColorFilter", slotColor(theme, upcoming[2].type))
            }
        } else {
            views.setTextViewText(R.id.widget_active_name, "Loading...")
            views.setTextViewText(R.id.widget_active_meaning, "")
            views.setTextViewText(R.id.widget_active_range, "")
            stopCountdown(views, R.id.widget_countdown, "00:00")
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
        val theme = WidgetTheme.from(context)
        applyMediumTheme(views, theme)
        val pendingIntent = getLaunchIntent(context)
        if (pendingIntent != null) {
            views.setOnClickPendingIntent(R.id.widget_detail_root, pendingIntent)
            views.setOnClickPendingIntent(R.id.widget_detail_label_current, pendingIntent)
        }

        val currentSlot = state.currentSlot
        val upcoming = state.nextSlots.take(3)
        clearMediumMetrics(views)
        clearMediumUpcoming(views)

        views.setTextViewText(R.id.widget_detail_label_current, currentShort(language))
        views.setTextViewText(R.id.widget_detail_label_left, remainingShort(language))

        if (currentSlot != null) {
            views.setTextViewText(R.id.widget_detail_active_name, AppLocalizer.slotName(currentSlot.type, language))
            
            views.setTextViewText(R.id.widget_detail_active_meaning, AppLocalizer.slotMeaning(currentSlot.type, language))
            
            val rangeStr = AppLocalizer.localizedRange(currentSlot.start, currentSlot.end, language, timezone)
            views.setTextViewText(R.id.widget_detail_active_range, rangeStr)
            
            views.setInt(R.id.widget_detail_dot, "setColorFilter", slotColor(theme, currentSlot.type))
            views.setTextColor(R.id.widget_detail_countdown, slotColor(theme, currentSlot.type))

            val now = System.currentTimeMillis()
            val diffSeconds = (currentSlot.end.time - now) / 1000
            val totalSeconds = (currentSlot.end.time - currentSlot.start.time) / 1000
            val progressPercent = if (totalSeconds > 0) {
                ((totalSeconds - diffSeconds).toFloat() / totalSeconds.toFloat() * 100).toInt()
            } else 0

            bindCountdown(views, R.id.widget_detail_countdown, currentSlot.end.time)
            views.setProgressBar(R.id.widget_detail_progress, 100, progressPercent.coerceIn(0, 100), false)

            if (upcoming.isNotEmpty()) {
                views.setTextViewText(R.id.widget_detail_next_1_name, AppLocalizer.slotName(upcoming[0].type, language))
                views.setTextViewText(R.id.widget_detail_next_1_time, AppLocalizer.localizedTime(upcoming[0].start, language, timezone))
                views.setInt(R.id.widget_detail_next_1_dot, "setColorFilter", slotColor(theme, upcoming[0].type))
            }
            if (upcoming.size >= 2) {
                views.setTextViewText(R.id.widget_detail_next_2_name, AppLocalizer.slotName(upcoming[1].type, language))
                views.setTextViewText(R.id.widget_detail_next_2_time, AppLocalizer.localizedTime(upcoming[1].start, language, timezone))
                views.setInt(R.id.widget_detail_next_2_dot, "setColorFilter", slotColor(theme, upcoming[1].type))
            }
            if (upcoming.size >= 3) {
                views.setTextViewText(R.id.widget_detail_next_3_name, AppLocalizer.slotName(upcoming[2].type, language))
                views.setTextViewText(R.id.widget_detail_next_3_time, AppLocalizer.localizedTime(upcoming[2].start, language, timezone))
                views.setInt(R.id.widget_detail_next_3_dot, "setColorFilter", slotColor(theme, upcoming[2].type))
            }
        } else {
            views.setTextViewText(R.id.widget_detail_active_name, "Loading...")
            views.setTextViewText(R.id.widget_detail_active_meaning, "")
            views.setTextViewText(R.id.widget_detail_active_range, "")
            stopCountdown(views, R.id.widget_detail_countdown, "--:--")
            views.setProgressBar(R.id.widget_detail_progress, 100, 0, false)
        }

        if (astroDay != null) {
            bindMetricTime(views, R.id.widget_metric_sunrise_time, R.id.widget_metric_sunrise_meridiem, astroDay.sunrise, language)
            bindMetricTime(views, R.id.widget_metric_sunset_time, R.id.widget_metric_sunset_meridiem, astroDay.sunset, language)
            bindMetricTime(views, R.id.widget_metric_moonrise_time, R.id.widget_metric_moonrise_meridiem, astroDay.moonrise, language)
            bindMetricTime(views, R.id.widget_metric_moonset_time, R.id.widget_metric_moonset_meridiem, astroDay.moonset, language)
            bindMetricTime(views, R.id.widget_metric_noon_time, R.id.widget_metric_noon_meridiem, astroDay.solarNoon, language)
            
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
        val theme = WidgetTheme.from(context)
        applyLargeTheme(views, theme)
        val pendingIntent = getLaunchIntent(context)
        if (pendingIntent != null) {
            views.setOnClickPendingIntent(R.id.widget_large_root, pendingIntent)
            views.setOnClickPendingIntent(R.id.widget_large_label_current, pendingIntent)
        }

        val currentSlot = state.currentSlot
        val upcoming = state.nextSlots.take(3)
        clearLargeMetrics(views)
        clearLargeUpcoming(views)
        clearLargeWindows(views)

        views.setTextViewText(R.id.widget_large_label_current, currentShort(language))
        views.setTextViewText(R.id.widget_large_label_left, remainingShort(language))

        if (currentSlot != null) {
            val name = AppLocalizer.slotName(currentSlot.type, language)
            views.setTextViewText(R.id.widget_large_active_name, name)
            
            views.setTextViewText(R.id.widget_large_active_meaning, AppLocalizer.slotMeaning(currentSlot.type, language))
            
            val rangeStr = AppLocalizer.localizedRange(currentSlot.start, currentSlot.end, language, timezone)
            views.setTextViewText(R.id.widget_large_active_range, rangeStr)
            
            views.setInt(R.id.widget_large_dot, "setColorFilter", slotColor(theme, currentSlot.type))
            views.setTextColor(R.id.widget_large_countdown, slotColor(theme, currentSlot.type))

            val now = System.currentTimeMillis()
            val diffSeconds = (currentSlot.end.time - now) / 1000
            val totalSeconds = (currentSlot.end.time - currentSlot.start.time) / 1000
            val progressPercent = if (totalSeconds > 0) {
                ((totalSeconds - diffSeconds).toFloat() / totalSeconds.toFloat() * 100).toInt()
            } else 0

            bindCountdown(views, R.id.widget_large_countdown, currentSlot.end.time)
            views.setProgressBar(R.id.widget_large_progress, 100, progressPercent.coerceIn(0, 100), false)

            if (upcoming.isNotEmpty()) {
                views.setTextViewText(R.id.widget_large_next_1_name, AppLocalizer.slotName(upcoming[0].type, language))
                views.setTextViewText(R.id.widget_large_next_1_time, AppLocalizer.localizedTime(upcoming[0].start, language, timezone))
                views.setInt(R.id.widget_large_next_1_dot, "setColorFilter", slotColor(theme, upcoming[0].type))
            }
            if (upcoming.size >= 2) {
                views.setTextViewText(R.id.widget_large_next_2_name, AppLocalizer.slotName(upcoming[1].type, language))
                views.setTextViewText(R.id.widget_large_next_2_time, AppLocalizer.localizedTime(upcoming[1].start, language, timezone))
                views.setInt(R.id.widget_large_next_2_dot, "setColorFilter", slotColor(theme, upcoming[1].type))
            }
            if (upcoming.size >= 3) {
                views.setTextViewText(R.id.widget_large_next_3_name, AppLocalizer.slotName(upcoming[2].type, language))
                views.setTextViewText(R.id.widget_large_next_3_time, AppLocalizer.localizedTime(upcoming[2].start, language, timezone))
                views.setInt(R.id.widget_large_next_3_dot, "setColorFilter", slotColor(theme, upcoming[2].type))
            }
        } else {
            views.setTextViewText(R.id.widget_large_active_name, "Loading...")
            views.setTextViewText(R.id.widget_large_active_meaning, "")
            views.setTextViewText(R.id.widget_large_active_range, "")
            stopCountdown(views, R.id.widget_large_countdown, "--:--")
            views.setProgressBar(R.id.widget_large_progress, 100, 0, false)
        }

        if (astroDay != null) {
            bindMetricTimeInline(theme.themedContext, views, R.id.widget_large_metric_sunrise, R.id.widget_large_metric_sunrise_meridiem, astroDay.sunrise, language, R.color.widget_metric_orange)
            bindMetricTimeInline(theme.themedContext, views, R.id.widget_large_metric_sunset, R.id.widget_large_metric_sunset_meridiem, astroDay.sunset, language, R.color.widget_metric_red)
            bindMetricTimeInline(theme.themedContext, views, R.id.widget_large_metric_moonrise, R.id.widget_large_metric_moonrise_meridiem, astroDay.moonrise, language, R.color.celestial_moon)
            bindMetricTimeInline(theme.themedContext, views, R.id.widget_large_metric_moonset, R.id.widget_large_metric_moonset_meridiem, astroDay.moonset, language, R.color.celestial_moon)
            bindMetricTimeInline(theme.themedContext, views, R.id.widget_large_metric_noon, R.id.widget_large_metric_noon_meridiem, astroDay.solarNoon, language, R.color.widget_metric_yellow)
            
            val phaseVal = (astroDay.illuminationFraction * 100).toInt()
            views.setTextViewText(R.id.widget_large_metric_phase, "$phaseVal%")
            
            val auspicious = astroDay.auspiciousWindows
            if (auspicious.isNotEmpty()) {
                views.setTextViewText(R.id.widget_large_auspicious_1_name, AppLocalizer.astronomyWindowTitle(auspicious[0].key, language))
                val t1 = compactWidgetRange(auspicious[0].startTime, auspicious[0].endTime, language)
                views.setTextViewText(R.id.widget_large_auspicious_1_time, t1)
            }
            if (auspicious.size > 1) {
                views.setTextViewText(R.id.widget_large_auspicious_2_name, AppLocalizer.astronomyWindowTitle(auspicious[1].key, language))
                val t2 = compactWidgetRange(auspicious[1].startTime, auspicious[1].endTime, language)
                views.setTextViewText(R.id.widget_large_auspicious_2_time, t2)
            }

            val inauspicious = astroDay.inauspiciousWindows
            if (inauspicious.isNotEmpty()) {
                views.setTextViewText(R.id.widget_large_inauspicious_1_name, AppLocalizer.astronomyWindowTitle(inauspicious[0].key, language))
                val t3 = compactWidgetRange(inauspicious[0].startTime, inauspicious[0].endTime, language)
                views.setTextViewText(R.id.widget_large_inauspicious_1_time, t3)
            }
            if (inauspicious.size > 1) {
                views.setTextViewText(R.id.widget_large_inauspicious_2_name, AppLocalizer.astronomyWindowTitle(inauspicious[1].key, language))
                val t4 = compactWidgetRange(inauspicious[1].startTime, inauspicious[1].endTime, language)
                views.setTextViewText(R.id.widget_large_inauspicious_2_time, t4)
            }
        }

        return views
    }

    private data class WidgetTheme(
        val themedContext: Context,
        val backgroundRes: Int,
        val text: Int,
        val muted: Int,
        val progressTrack: Int,
        val accentBlue: Int,
        val metricOrange: Int,
        val metricRed: Int,
        val metricYellow: Int,
        val moon: Int
    ) {
        companion object {
            fun from(context: Context): WidgetTheme {
                val config = Configuration(context.resources.configuration)
                val systemNightMode = config.uiMode and Configuration.UI_MODE_NIGHT_MASK
                val effectiveNightMode = when (AppState(context).themeMode) {
                    AppThemeMode.LIGHT -> Configuration.UI_MODE_NIGHT_NO
                    AppThemeMode.DARK -> Configuration.UI_MODE_NIGHT_YES
                    AppThemeMode.SYSTEM -> if (systemNightMode == Configuration.UI_MODE_NIGHT_YES) {
                        Configuration.UI_MODE_NIGHT_YES
                    } else {
                        Configuration.UI_MODE_NIGHT_NO
                    }
                }
                config.uiMode = (config.uiMode and Configuration.UI_MODE_NIGHT_MASK.inv()) or effectiveNightMode
                val themedContext = context.createConfigurationContext(config)
                val isDark = effectiveNightMode == Configuration.UI_MODE_NIGHT_YES

                return WidgetTheme(
                    themedContext = themedContext,
                    backgroundRes = if (isDark) R.drawable.bg_widget_card_dark else R.drawable.bg_widget_card_light,
                    text = ContextCompat.getColor(themedContext, R.color.celestial_text),
                    muted = ContextCompat.getColor(themedContext, R.color.celestial_text_muted),
                    progressTrack = ContextCompat.getColor(themedContext, R.color.widget_progress_track),
                    accentBlue = ContextCompat.getColor(themedContext, R.color.widget_accent_blue),
                    metricOrange = ContextCompat.getColor(themedContext, R.color.widget_metric_orange),
                    metricRed = ContextCompat.getColor(themedContext, R.color.widget_metric_red),
                    metricYellow = ContextCompat.getColor(themedContext, R.color.widget_metric_yellow),
                    moon = ContextCompat.getColor(themedContext, R.color.celestial_moon)
                )
            }
        }
    }

    private fun applySmallTheme(views: RemoteViews, theme: WidgetTheme) {
        views.setInt(R.id.widget_root, "setBackgroundResource", theme.backgroundRes)
        applyTextColor(
            views,
            theme.text,
            R.id.widget_active_name,
            R.id.widget_countdown,
            R.id.widget_next_1_name,
            R.id.widget_next_2_name,
            R.id.widget_next_3_name
        )
        applyTextColor(
            views,
            theme.muted,
            R.id.widget_label_current,
            R.id.widget_active_meaning,
            R.id.widget_label_left,
            R.id.widget_active_range,
            R.id.widget_next_1_time,
            R.id.widget_next_2_time,
            R.id.widget_next_3_time
        )
        tintProgress(views, R.id.widget_progress, theme)
    }

    private fun applyMediumTheme(views: RemoteViews, theme: WidgetTheme) {
        views.setInt(R.id.widget_detail_root, "setBackgroundResource", theme.backgroundRes)
        applyTextColor(
            views,
            theme.text,
            R.id.widget_metric_sunrise_time,
            R.id.widget_metric_sunset_time,
            R.id.widget_metric_moonrise_time,
            R.id.widget_metric_moonset_time,
            R.id.widget_metric_noon_time,
            R.id.widget_metric_illumination_value,
            R.id.widget_detail_active_name,
            R.id.widget_detail_countdown,
            R.id.widget_detail_next_1_name,
            R.id.widget_detail_next_2_name,
            R.id.widget_detail_next_3_name
        )
        applyTextColor(
            views,
            theme.muted,
            R.id.widget_metric_sunrise_label,
            R.id.widget_metric_sunset_label,
            R.id.widget_metric_moonrise_label,
            R.id.widget_metric_moonset_label,
            R.id.widget_metric_noon_label,
            R.id.widget_metric_illumination_label,
            R.id.widget_detail_label_current,
            R.id.widget_detail_active_meaning,
            R.id.widget_detail_label_left,
            R.id.widget_detail_active_range,
            R.id.widget_detail_next_1_time,
            R.id.widget_detail_next_2_time,
            R.id.widget_detail_next_3_time
        )
        views.setTextColor(R.id.widget_metric_sunrise_meridiem, theme.metricOrange)
        views.setTextColor(R.id.widget_metric_sunset_meridiem, theme.metricRed)
        views.setTextColor(R.id.widget_metric_moonrise_meridiem, theme.moon)
        views.setTextColor(R.id.widget_metric_moonset_meridiem, theme.moon)
        views.setTextColor(R.id.widget_metric_noon_meridiem, theme.metricYellow)
        views.setTextColor(R.id.widget_detail_next_label, theme.metricYellow)
        tintProgress(views, R.id.widget_detail_progress, theme)
    }

    private fun applyLargeTheme(views: RemoteViews, theme: WidgetTheme) {
        views.setInt(R.id.widget_large_root, "setBackgroundResource", theme.backgroundRes)
        applyTextColor(
            views,
            theme.text,
            R.id.widget_large_active_name,
            R.id.widget_large_countdown,
            R.id.widget_large_metric_sunrise,
            R.id.widget_large_metric_sunset,
            R.id.widget_large_metric_noon,
            R.id.widget_large_metric_moonrise,
            R.id.widget_large_metric_moonset,
            R.id.widget_large_metric_phase,
            R.id.widget_large_next_1_name,
            R.id.widget_large_next_2_name,
            R.id.widget_large_next_3_name
        )
        applyTextColor(
            views,
            theme.muted,
            R.id.widget_large_label_current,
            R.id.widget_large_active_range,
            R.id.widget_large_label_left,
            R.id.widget_large_active_meaning,
            R.id.widget_large_metric_sunrise_label,
            R.id.widget_large_metric_sunset_label,
            R.id.widget_large_metric_noon_label,
            R.id.widget_large_metric_moonrise_label,
            R.id.widget_large_metric_moonset_label,
            R.id.widget_large_metric_phase_label,
            R.id.widget_large_next_1_time,
            R.id.widget_large_next_2_time,
            R.id.widget_large_next_3_time
        )
        views.setTextColor(R.id.widget_large_metric_sunrise_meridiem, theme.metricOrange)
        views.setTextColor(R.id.widget_large_metric_sunset_meridiem, theme.metricRed)
        views.setTextColor(R.id.widget_large_metric_noon_meridiem, theme.metricYellow)
        views.setTextColor(R.id.widget_large_metric_moonrise_meridiem, theme.moon)
        views.setTextColor(R.id.widget_large_metric_moonset_meridiem, theme.moon)
        views.setTextColor(R.id.widget_large_next_label, theme.metricYellow)
        tintProgress(views, R.id.widget_large_progress, theme)
    }

    private fun applyTextColor(views: RemoteViews, color: Int, vararg ids: Int) {
        ids.forEach { id -> views.setTextColor(id, color) }
    }

    private fun tintProgress(views: RemoteViews, progressId: Int, theme: WidgetTheme) {
        views.setColorStateList(progressId, "setProgressTintList", ColorStateList.valueOf(theme.accentBlue))
        views.setColorStateList(progressId, "setProgressBackgroundTintList", ColorStateList.valueOf(theme.progressTrack))
    }

    private fun slotColor(theme: WidgetTheme, type: String): Int {
        return ContextCompat.getColor(theme.themedContext, getSlotColorRes(type))
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

    private fun remainingShort(language: AppLanguage): String {
        return when (language.localizedContentLanguage) {
            LocalizedContentLanguage.ENGLISH -> "Left"
            LocalizedContentLanguage.HINDI -> "शेष"
            LocalizedContentLanguage.GUJARATI -> "બાકી"
        }
    }

    private fun currentShort(language: AppLanguage): String {
        return when (language.localizedContentLanguage) {
            LocalizedContentLanguage.ENGLISH -> "Current"
            LocalizedContentLanguage.HINDI -> "वर्तमान"
            LocalizedContentLanguage.GUJARATI -> "હાલ"
        }
    }

    private fun compactWidgetTime(raw: String, language: AppLanguage): String {
        return AppLocalizer.localizedTime(raw, language).compactForWidget()
    }

    private fun compactWidgetRange(start: String, end: String, language: AppLanguage): String {
        return "${compactWidgetTime(start, language)}-${compactWidgetTime(end, language)}"
    }

    private fun String.compactForWidget(): String {
        return replace(" AM", "am")
            .replace(" PM", "pm")
            .replace(" am", "am")
            .replace(" pm", "pm")
            .replace(" ", "")
    }

    private fun bindMetricTime(
        views: RemoteViews,
        timeViewId: Int,
        meridiemViewId: Int,
        raw: String?,
        language: AppLanguage
    ) {
        if (raw.isNullOrBlank()) {
            views.setTextViewText(timeViewId, "--")
            views.setTextViewText(meridiemViewId, "")
            return
        }

        val parts = splitMeridiem(AppLocalizer.localizedTime(raw, language))
        views.setTextViewText(timeViewId, parts.first)
        views.setTextViewText(meridiemViewId, parts.second)
    }

    private fun bindMetricTimeInline(
        context: Context,
        views: RemoteViews,
        timeViewId: Int,
        meridiemViewId: Int,
        raw: String?,
        language: AppLanguage,
        meridiemColorRes: Int
    ) {
        views.setViewVisibility(meridiemViewId, View.GONE)
        if (raw.isNullOrBlank()) {
            views.setTextViewText(timeViewId, "--")
            return
        }

        val (time, meridiem) = splitMeridiem(AppLocalizer.localizedTime(raw, language))
        if (meridiem.isBlank()) {
            views.setTextViewText(timeViewId, time)
            return
        }

        val text = "$time $meridiem"
        val span = SpannableString(text)
        val start = text.length - meridiem.length
        span.setSpan(ForegroundColorSpan(ContextCompat.getColor(context, meridiemColorRes)), start, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(RelativeSizeSpan(0.72f), start, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        views.setTextViewText(timeViewId, span)
    }

    private fun splitMeridiem(localizedTime: String): Pair<String, String> {
        val trimmed = localizedTime.trim()
        val splitAt = trimmed.lastIndexOf(' ')
        if (splitAt > 0 && splitAt < trimmed.length - 1) {
            return trimmed.substring(0, splitAt) to trimmed.substring(splitAt + 1)
        }
        val meridiem = when {
            trimmed.endsWith("AM", ignoreCase = true) -> "AM"
            trimmed.endsWith("PM", ignoreCase = true) -> "PM"
            else -> ""
        }
        val value = if (meridiem.isEmpty()) trimmed else trimmed.dropLast(2).trim()
        return value to meridiem
    }

    private fun bindCountdown(views: RemoteViews, viewId: Int, endTimeMillis: Long) {
        val remainingMillis = endTimeMillis - System.currentTimeMillis()
        if (remainingMillis > 0) {
            views.setChronometerCountDown(viewId, true)
            views.setChronometer(viewId, SystemClock.elapsedRealtime() + remainingMillis, null, true)
        } else {
            stopCountdown(views, viewId, "00:00")
        }
    }

    private fun stopCountdown(views: RemoteViews, viewId: Int, text: String) {
        views.setChronometer(viewId, SystemClock.elapsedRealtime(), null, false)
        views.setTextViewText(viewId, text)
    }

    private fun clearSmallUpcoming(views: RemoteViews) {
        views.setTextViewText(R.id.widget_next_1_name, "--")
        views.setTextViewText(R.id.widget_next_1_time, "")
        views.setTextViewText(R.id.widget_next_2_name, "--")
        views.setTextViewText(R.id.widget_next_2_time, "")
        views.setTextViewText(R.id.widget_next_3_name, "--")
        views.setTextViewText(R.id.widget_next_3_time, "")
    }

    private fun clearMediumMetrics(views: RemoteViews) {
        views.setTextViewText(R.id.widget_metric_sunrise_time, "--:--")
        views.setTextViewText(R.id.widget_metric_sunrise_meridiem, "")
        views.setTextViewText(R.id.widget_metric_sunset_time, "--:--")
        views.setTextViewText(R.id.widget_metric_sunset_meridiem, "")
        views.setTextViewText(R.id.widget_metric_moonrise_time, "--:--")
        views.setTextViewText(R.id.widget_metric_moonrise_meridiem, "")
        views.setTextViewText(R.id.widget_metric_moonset_time, "--:--")
        views.setTextViewText(R.id.widget_metric_moonset_meridiem, "")
        views.setTextViewText(R.id.widget_metric_noon_time, "--:--")
        views.setTextViewText(R.id.widget_metric_noon_meridiem, "")
        views.setTextViewText(R.id.widget_metric_illumination_value, "--")
    }

    private fun clearMediumUpcoming(views: RemoteViews) {
        views.setTextViewText(R.id.widget_detail_next_1_name, "--")
        views.setTextViewText(R.id.widget_detail_next_1_time, "")
        views.setTextViewText(R.id.widget_detail_next_2_name, "--")
        views.setTextViewText(R.id.widget_detail_next_2_time, "")
        views.setTextViewText(R.id.widget_detail_next_3_name, "--")
        views.setTextViewText(R.id.widget_detail_next_3_time, "")
    }

    private fun clearLargeMetrics(views: RemoteViews) {
        views.setTextViewText(R.id.widget_large_metric_sunrise, "--:--")
        views.setTextViewText(R.id.widget_large_metric_sunrise_meridiem, "")
        views.setTextViewText(R.id.widget_large_metric_sunset, "--:--")
        views.setTextViewText(R.id.widget_large_metric_sunset_meridiem, "")
        views.setTextViewText(R.id.widget_large_metric_moonrise, "--:--")
        views.setTextViewText(R.id.widget_large_metric_moonrise_meridiem, "")
        views.setTextViewText(R.id.widget_large_metric_moonset, "--:--")
        views.setTextViewText(R.id.widget_large_metric_moonset_meridiem, "")
        views.setTextViewText(R.id.widget_large_metric_noon, "--:--")
        views.setTextViewText(R.id.widget_large_metric_noon_meridiem, "")
        views.setTextViewText(R.id.widget_large_metric_phase, "--")
    }

    private fun clearLargeUpcoming(views: RemoteViews) {
        views.setTextViewText(R.id.widget_large_next_1_name, "--")
        views.setTextViewText(R.id.widget_large_next_1_time, "")
        views.setTextViewText(R.id.widget_large_next_2_name, "--")
        views.setTextViewText(R.id.widget_large_next_2_time, "")
        views.setTextViewText(R.id.widget_large_next_3_name, "--")
        views.setTextViewText(R.id.widget_large_next_3_time, "")
    }

    private fun clearLargeWindows(views: RemoteViews) {
        views.setTextViewText(R.id.widget_large_auspicious_1_name, "--")
        views.setTextViewText(R.id.widget_large_auspicious_1_time, "")
        views.setTextViewText(R.id.widget_large_auspicious_2_name, "--")
        views.setTextViewText(R.id.widget_large_auspicious_2_time, "")
        views.setTextViewText(R.id.widget_large_inauspicious_1_name, "--")
        views.setTextViewText(R.id.widget_large_inauspicious_1_time, "")
        views.setTextViewText(R.id.widget_large_inauspicious_2_name, "--")
        views.setTextViewText(R.id.widget_large_inauspicious_2_time, "")
    }
}
