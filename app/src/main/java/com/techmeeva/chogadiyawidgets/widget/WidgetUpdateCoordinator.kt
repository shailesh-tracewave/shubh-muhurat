package com.techmeeva.chogadiyawidgets.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import com.techmeeva.chogadiyawidgets.core.state.AppState
import com.techmeeva.chogadiyawidgets.core.state.ChoghadiyaDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date

object WidgetUpdateCoordinator {

    fun showPlaceholder(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        val views = WidgetResponsiveBuilder.buildPlaceholder(context)
        appWidgetIds.forEach { appWidgetManager.updateAppWidget(it, views) }
    }

    suspend fun update(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
        includeAstronomy: Boolean
    ) {
        try {
            val appState = AppState(context)
            val dataStore = ChoghadiyaDataStore(context)
            val now = Date()

            dataStore.loadHome(
                baseURL = appState.apiBaseURL,
                city = appState.selectedCity,
                subscriptionStatus = appState.subscriptionPlan.rawValue,
                language = appState.selectedLanguage,
                forceRefresh = false
            )

            if (includeAstronomy) {
                dataStore.loadAstronomy(
                    baseURL = appState.apiBaseURL,
                    city = appState.selectedCity,
                    date = now,
                    subscriptionStatus = appState.subscriptionPlan.rawValue,
                    language = appState.selectedLanguage,
                    forceRefresh = false
                )
            }

            val state = dataStore.getHomeState(appState.selectedCity)
            val astroDay = if (includeAstronomy) {
                dataStore.getAstronomyState(appState.selectedCity, now).day
            } else {
                null
            }

            val views = WidgetResponsiveBuilder.build(
                context,
                state,
                astroDay,
                appState.selectedLanguage,
                appState.selectedCity.timezone
            )

            withContext(Dispatchers.Main) {
                appWidgetIds.forEach { appWidgetManager.updateAppWidget(it, views) }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                showPlaceholder(context, appWidgetManager, appWidgetIds)
            }
        }
    }
}
