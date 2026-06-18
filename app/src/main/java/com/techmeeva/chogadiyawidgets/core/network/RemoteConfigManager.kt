package com.techmeeva.chogadiyawidgets.core.network

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteConfigManager(private val context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("remote_config_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val service = RemoteConfigService(context)

    var currentConfig: RemoteAppConfig
        get() {
            val json = prefs.getString("cached_config", null)
            return if (json != null) {
                try {
                    gson.fromJson(json, RemoteAppConfig::class.java)
                } catch (e: Exception) {
                    RemoteAppConfig()
                }
            } else {
                RemoteAppConfig()
            }
        }
        private set(value) {
            prefs.edit().putString("cached_config", gson.toJson(value)).apply()
        }

    suspend fun refreshConfig() {
        val newConfig = service.fetchConfig()
        if (newConfig != null) {
            currentConfig = newConfig
        }
    }
}
