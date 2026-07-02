package com.techmeeva.chogadiyawidgets.core.state

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkerParameters
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.ExistingWorkPolicy
import com.techmeeva.chogadiyawidgets.widget.TimelineWidgetProvider
import com.techmeeva.chogadiyawidgets.widget.DetailCardWidgetProvider
import com.techmeeva.chogadiyawidgets.widget.LargeWidgetProvider
import com.techmeeva.chogadiyawidgets.widget.WidgetResponsiveBuilder
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

            val nowDate = Date()
            dataStore.loadAstronomy(
                baseURL = appState.apiBaseURL,
                city = appState.selectedCity,
                date = nowDate,
                subscriptionStatus = appState.subscriptionPlan.rawValue,
                language = appState.selectedLanguage,
                forceRefresh = true
            )

            // 2. Update widgets directly from the freshly loaded/cache-backed state.
            updateWidgetsFromStore(appState, dataStore, nowDate)

            // 3. Schedule next boundary transition
            val state = dataStore.getHomeState(appState.selectedCity)
            val astronomyState = dataStore.getAstronomyState(appState.selectedCity, nowDate)
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

            return if ((state.errorMessage != null && !state.hasContent) ||
                (astronomyState.errorMessage != null && !astronomyState.hasContent)
            ) {
                Result.retry()
            } else {
                Result.success()
            }
        } catch (e: Exception) {
            runCatching { updateWidgetsFromStore(appState, dataStore, Date()) }
            return Result.retry()
        }
    }

    private fun updateWidgetsFromStore(
        appState: AppState,
        dataStore: ChoghadiyaDataStore,
        date: Date
    ) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val widgetIds = allWidgetIds(appWidgetManager)
        if (widgetIds.isEmpty()) return

        val views = WidgetResponsiveBuilder.build(
            context = context,
            state = dataStore.getHomeState(appState.selectedCity),
            astroDay = dataStore.getAstronomyState(appState.selectedCity, date).day,
            language = appState.selectedLanguage,
            timezone = appState.selectedCity.timezone
        )
        widgetIds.forEach { appWidgetManager.updateAppWidget(it, views) }
    }

    private fun allWidgetIds(appWidgetManager: AppWidgetManager): IntArray {
        return appWidgetManager.getAppWidgetIds(ComponentName(context, TimelineWidgetProvider::class.java)) +
            appWidgetManager.getAppWidgetIds(ComponentName(context, DetailCardWidgetProvider::class.java)) +
            appWidgetManager.getAppWidgetIds(ComponentName(context, LargeWidgetProvider::class.java))
    }

    companion object {
        fun enqueueInitial(context: Context) {
            val workRequest = OneTimeWorkRequestBuilder<WidgetUpdateWorker>()
                .setInitialDelay(0, TimeUnit.MILLISECONDS)
                .build()

            WorkManager.getInstance(context).enqueueUniqueWork(
                "widget_refresh_now",
                ExistingWorkPolicy.REPLACE,
                workRequest
            )
        }

        fun enqueuePeriodic(context: Context) {
            val workRequest = PeriodicWorkRequestBuilder<WidgetUpdateWorker>(15, TimeUnit.MINUTES)
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "widget_refresh_periodic",
                ExistingPeriodicWorkPolicy.UPDATE,
                workRequest
            )
        }
    }
}
