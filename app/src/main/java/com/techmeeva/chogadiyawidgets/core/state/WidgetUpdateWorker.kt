package com.techmeeva.chogadiyawidgets.core.state

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.ExistingWorkPolicy
import com.techmeeva.chogadiyawidgets.widget.TimelineWidgetProvider
import com.techmeeva.chogadiyawidgets.widget.DetailCardWidgetProvider
import com.techmeeva.chogadiyawidgets.widget.LargeWidgetProvider
import java.util.Date
import java.util.concurrent.TimeUnit

class WidgetUpdateWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val appState = AppState(context)
        val dataStore = ChoghadiyaDataStore(context)

        try {
            // 1. Fetch fresh data and update Room Database cache
            dataStore.loadHome(
                baseURL = appState.apiBaseURL,
                city = appState.selectedCity,
                subscriptionStatus = appState.subscriptionPlan.rawValue,
                language = appState.selectedLanguage,
                forceRefresh = true // Force reload fresh data
            )

            dataStore.loadAstronomy(
                baseURL = appState.apiBaseURL,
                city = appState.selectedCity,
                date = Date(),
                subscriptionStatus = appState.subscriptionPlan.rawValue,
                language = appState.selectedLanguage,
                forceRefresh = true
            )

            // 2. Broadcast updates to widgets
            triggerWidgetRedraw()

            // 3. Schedule next boundary transition
            val state = dataStore.getHomeState(appState.selectedCity)
            val currentSlot = state.currentSlot
            if (currentSlot != null) {
                val now = System.currentTimeMillis()
                val endTime = currentSlot.end.time
                val delayMs = endTime - now

                if (delayMs > 0) {
                    val workRequest = OneTimeWorkRequestBuilder<WidgetUpdateWorker>()
                        .setInitialDelay(delayMs + 1000, TimeUnit.MILLISECONDS) // Buffer 1s to ensure boundary is crossed
                        .build()

                    WorkManager.getInstance(context).enqueueUniqueWork(
                        "slot_boundary_update",
                        ExistingWorkPolicy.REPLACE,
                        workRequest
                    )
                }
            }

            return Result.success()
        } catch (e: Exception) {
            return Result.retry()
        }
    }

    private fun triggerWidgetRedraw() {
        // Redraw Timeline Widget
        val timelineIntent = Intent(context, TimelineWidgetProvider::class.java).apply {
            action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val ids = appWidgetManager.getAppWidgetIds(ComponentName(context, TimelineWidgetProvider::class.java))
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
        }
        context.sendBroadcast(timelineIntent)

        // Redraw Detail Card Widget
        val detailIntent = Intent(context, DetailCardWidgetProvider::class.java).apply {
            action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val ids = appWidgetManager.getAppWidgetIds(ComponentName(context, DetailCardWidgetProvider::class.java))
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
        }
        context.sendBroadcast(detailIntent)

        // Redraw Large Widget
        val largeIntent = Intent(context, LargeWidgetProvider::class.java).apply {
            action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val ids = appWidgetManager.getAppWidgetIds(ComponentName(context, LargeWidgetProvider::class.java))
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
        }
        context.sendBroadcast(largeIntent)
    }

    companion object {
        fun enqueueInitial(context: Context) {
            val workRequest = OneTimeWorkRequestBuilder<WidgetUpdateWorker>()
                .setInitialDelay(0, TimeUnit.MILLISECONDS)
                .build()

            WorkManager.getInstance(context).enqueueUniqueWork(
                "slot_boundary_update",
                ExistingWorkPolicy.KEEP,
                workRequest
            )
        }
    }
}
