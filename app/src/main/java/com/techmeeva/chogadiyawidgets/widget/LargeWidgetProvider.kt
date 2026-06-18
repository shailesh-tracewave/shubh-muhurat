package com.techmeeva.chogadiyawidgets.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.widget.RemoteViews
import androidx.core.content.ContextCompat
import com.techmeeva.chogadiyawidgets.R
import com.techmeeva.chogadiyawidgets.core.localization.AppLocalizer
import com.techmeeva.chogadiyawidgets.core.state.AppState
import com.techmeeva.chogadiyawidgets.core.state.ChoghadiyaDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class LargeWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // Show immediate placeholder so widget never shows "Can't load"
        for (appWidgetId in appWidgetIds) {
            val views = WidgetResponsiveBuilder.buildPlaceholder(context)
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

        // Use goAsync() to keep receiver alive during coroutine work
        val pendingResult = goAsync()
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            try {
                val appState = AppState(context)
                val dataStore = ChoghadiyaDataStore(context)

                // Try to load cached data
                dataStore.loadHome(
                    baseURL = appState.apiBaseURL,
                    city = appState.selectedCity,
                    subscriptionStatus = "premium_yearly",
                    language = appState.selectedLanguage,
                    forceRefresh = false
                )

                dataStore.loadAstronomy(
                    baseURL = appState.apiBaseURL,
                    city = appState.selectedCity,
                    date = java.util.Date(),
                    subscriptionStatus = "premium_yearly",
                    language = appState.selectedLanguage,
                    forceRefresh = false
                )

                val state = dataStore.getHomeState(appState.selectedCity)
                val currentSlot = state.currentSlot
                val upcoming = state.nextSlots.take(3)
                val astroState = dataStore.getAstronomyState(appState.selectedCity, java.util.Date())
                val astroDay = astroState.day

                withContext(Dispatchers.Main) {
                    val views = WidgetResponsiveBuilder.build(
                        context,
                        state,
                        astroDay,
                        appState.selectedLanguage,
                        appState.selectedCity.timezone
                    )
                    for (appWidgetId in appWidgetIds) {
                        appWidgetManager.updateAppWidget(appWidgetId, views)
                    }
                }
            } catch (e: Exception) {
                // On any error, show a safe fallback
                withContext(Dispatchers.Main) {
                    val views = WidgetResponsiveBuilder.buildPlaceholder(context)
                    for (appWidgetId in appWidgetIds) {
                        appWidgetManager.updateAppWidget(appWidgetId, views)
                    }
                }
            } finally {
                pendingResult.finish()
            }
        }
    }


}
