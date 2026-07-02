package com.techmeeva.chogadiyawidgets.widget

import android.appwidget.AppWidgetManager
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import com.techmeeva.chogadiyawidgets.core.state.WidgetUpdateWorker

class WidgetRefreshReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (!hasActiveWidgets(context)) return

        WidgetUpdateWorker.enqueueInitial(context)
        WidgetUpdateWorker.enqueuePeriodic(context)
    }

    private fun hasActiveWidgets(context: Context): Boolean {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        return appWidgetManager.getAppWidgetIds(ComponentName(context, TimelineWidgetProvider::class.java)).isNotEmpty() ||
            appWidgetManager.getAppWidgetIds(ComponentName(context, DetailCardWidgetProvider::class.java)).isNotEmpty() ||
            appWidgetManager.getAppWidgetIds(ComponentName(context, LargeWidgetProvider::class.java)).isNotEmpty()
    }
}
