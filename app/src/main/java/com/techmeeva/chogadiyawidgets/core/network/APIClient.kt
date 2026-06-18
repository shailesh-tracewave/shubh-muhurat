package com.techmeeva.chogadiyawidgets.core.network

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.techmeeva.chogadiyawidgets.models.ChoghadiyaDayResponse
import com.techmeeva.chogadiyawidgets.models.ChoghadiyaRangeResponse
import com.techmeeva.chogadiyawidgets.models.SeedCity
import com.techmeeva.chogadiyawidgets.models.SolarLunarDayResponse
import kotlinx.coroutines.delay
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit

sealed class APIError(message: String, cause: Throwable? = null) : Exception(message, cause) {
    object InvalidURL : APIError("The service configuration is invalid.")
    object InvalidResponse : APIError("The service returned an unreadable response.")
    class HTTPError(val code: Int) : APIError(
        if (code == 401 || code == 403) "Your session is no longer authorized."
        else "The service is currently unavailable. Please try again."
    )
    object TimedOut : APIError("The request took too long. Please try again.")
    object NetworkUnavailable : APIError("A network connection is required to refresh Choghadiya data.")
}

class APIClient {

    private val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, JsonDeserializer { json, _, _ ->
            val value = json.asString
            parseISO8601(value) ?: throw IOException("Unsupported ISO8601 date: $value")
        })
        .create()

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private fun getApi(baseURL: String): ChoghadiyaApi {
        val sanitizedURL = if (baseURL.endsWith("/")) baseURL else "$baseURL/"
        return Retrofit.Builder()
            .baseUrl(sanitizedURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ChoghadiyaApi::class.java)
    }

    suspend fun fetchDay(baseURL: String, city: SeedCity, date: Date): ChoghadiyaDayResponse {
        return performWithRetry {
            getApi(baseURL).fetchDay(
                lat = city.lat,
                lng = city.lng,
                date = apiDateString(date, city.timezone)
            )
        }
    }

    suspend fun fetchRange(baseURL: String, city: SeedCity, startDate: Date, days: Int): ChoghadiyaRangeResponse {
        return performWithRetry {
            getApi(baseURL).fetchRange(
                lat = city.lat,
                lng = city.lng,
                startDate = apiDateString(startDate, city.timezone),
                days = days
            )
        }
    }

    suspend fun fetchAstronomyDay(baseURL: String, city: SeedCity, date: Date): SolarLunarDayResponse {
        return performWithRetry {
            getApi(baseURL).fetchAstronomyDay(
                lat = city.lat,
                lng = city.lng,
                date = apiDateString(date, city.timezone)
            )
        }
    }

    private suspend fun <T> performWithRetry(block: suspend () -> T): T {
        val retryDelays = listOf(0L, 500L, 1000L)
        var lastError: Throwable? = null

        for ((index, delay) in retryDelays.withIndex()) {
            if (delay > 0) {
                delay(delay)
            }

            try {
                return block()
            } catch (e: retrofit2.HttpException) {
                val code = e.code()
                if (code >= 500 && index < retryDelays.size - 1) {
                    lastError = e
                    continue
                }
                throw APIError.HTTPError(code)
            } catch (e: SocketTimeoutException) {
                lastError = e
                if (index < retryDelays.size - 1) continue
                throw APIError.TimedOut
            } catch (e: UnknownHostException) {
                throw APIError.NetworkUnavailable
            } catch (e: IOException) {
                lastError = e
                if (index < retryDelays.size - 1) continue
                throw APIError.InvalidResponse
            } catch (e: Exception) {
                throw APIError.InvalidResponse
            }
        }
        throw lastError ?: APIError.InvalidResponse
    }

    private fun parseISO8601(value: String): Date? {
        val formats = listOf(
            "yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX",
            "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
            "yyyy-MM-dd'T'HH:mm:ssXXX",
            "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'",
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            "yyyy-MM-dd'T'HH:mm:ss'Z'",
            "yyyy-MM-dd'T'HH:mm:ss"
        )
        for (format in formats) {
            try {
                val sdf = SimpleDateFormat(format, Locale.US)
                if (format.endsWith("'Z'") || format.contains("Z")) {
                    sdf.timeZone = TimeZone.getTimeZone("UTC")
                }
                return sdf.parse(value)
            } catch (e: Exception) {
                // Try next
            }
        }
        return null
    }

    private fun apiDateString(date: Date, timezone: String): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US).apply {
            timeZone = TimeZone.getTimeZone(timezone)
        }
        return sdf.format(date)
    }
}
