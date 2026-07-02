package com.techmeeva.chogadiyawidgets.core.network

import android.util.Log
import com.google.gson.Gson
import com.techmeeva.chogadiyawidgets.core.state.AppConfiguration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

class RemoteConfigService {

    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()
        
    private val gson = Gson()

    suspend fun fetchConfig(): RemoteAppConfig? = withContext(Dispatchers.IO) {
        try {
            val url = AppConfiguration.remoteConfigURL
            val request = Request.Builder().url(url).build()
            
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    Log.e("RemoteConfig", "Failed to fetch config: ${response.code}")
                    return@withContext null
                }
                val bodyString = response.body?.string()
                if (bodyString != null) {
                    return@withContext gson.fromJson(bodyString, RemoteAppConfig::class.java)
                }
            }
        } catch (e: Exception) {
            Log.e("RemoteConfig", "Error fetching config", e)
        }
        return@withContext null
    }
}
