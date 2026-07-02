package com.techmeeva.chogadiyawidgets.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import com.techmeeva.chogadiyawidgets.core.state.WidgetUpdateWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailCardWidgetProvider : AppWidgetProvider() {

    override fun onEnabled(context: Context) {
        WidgetUpdateWorker.enqueueInitial(context)
        WidgetUpdateWorker.enqueuePeriodic(context)
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        WidgetUpdateWorker.enqueuePeriodic(context)
        WidgetUpdateCoordinator.showPlaceholder(context, appWidgetManager, appWidgetIds)

        val pendingResult = goAsync()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                WidgetUpdateCoordinator.update(
                    context = context,
                    appWidgetManager = appWidgetManager,
                    appWidgetIds = appWidgetIds,
                    includeAstronomy = true
                )
            } finally {
                pendingResult.finish()
            }
        }
    }
}
