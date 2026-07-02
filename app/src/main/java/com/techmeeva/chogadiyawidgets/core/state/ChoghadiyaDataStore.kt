package com.techmeeva.chogadiyawidgets.core.state

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.techmeeva.chogadiyawidgets.core.TimelineBuilder
import com.techmeeva.chogadiyawidgets.core.database.CacheEntry
import com.techmeeva.chogadiyawidgets.core.database.ChoghadiyaDatabase
import com.techmeeva.chogadiyawidgets.core.localization.AppLanguage
import com.techmeeva.chogadiyawidgets.core.network.APIClient
import com.techmeeva.chogadiyawidgets.models.ChoghadiyaDay
import com.techmeeva.chogadiyawidgets.models.ChoghadiyaRangeResponse
import com.techmeeva.chogadiyawidgets.models.SeedCity
import com.techmeeva.chogadiyawidgets.models.SolarLunarDay
import com.techmeeva.chogadiyawidgets.models.SolarLunarDayResponse
import com.techmeeva.chogadiyawidgets.models.TimelineSlot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

data class HomeTimelineState(
    var days: List<ChoghadiyaDay> = emptyList(),
    var currentSlot: TimelineSlot? = null,
    var nextSlots: List<TimelineSlot> = emptyList(),
    var provider: String = "",
    var lastUpdated: Date? = null,
    var sunrise: String = "--:--",
    var sunset: String = "--:--",
    var isLoading: Boolean = false,
    var isRefreshing: Boolean = false,
    var errorMessage: String? = null
) {
    val hasContent: Boolean
        get() = currentSlot != null || nextSlots.isNotEmpty() || days.isNotEmpty()
}

data class MonthScheduleState(
    var days: List<ChoghadiyaDay> = emptyList(),
    var provider: String = "",
    var lastUpdated: Date? = null,
    var isLoading: Boolean = false,
    var isRefreshing: Boolean = false,
    var errorMessage: String? = null
) {
    val hasContent: Boolean
        get() = days.isNotEmpty()
}

data class AstronomyState(
    var day: SolarLunarDay? = null,
    var provider: String = "",
    var lastUpdated: Date? = null,
    var isLoading: Boolean = false,
    var isRefreshing: Boolean = false,
    var errorMessage: String? = null
) {
    val hasContent: Boolean
        get() = day != null
}

class ChoghadiyaDataStore(context: Context) {

    private val db = ChoghadiyaDatabase.getDatabase(context)
    private val cacheDao = db.cacheDao()
    private val apiClient = APIClient()
    private val gson = Gson()

    private val _homeStates = MutableLiveData<Map<String, HomeTimelineState>>(emptyMap())
    val homeStates: LiveData<Map<String, HomeTimelineState>> = _homeStates

    private val _monthStates = MutableLiveData<Map<String, MonthScheduleState>>(emptyMap())
    val monthStates: LiveData<Map<String, MonthScheduleState>> = _monthStates

    private val _astronomyStates = MutableLiveData<Map<String, AstronomyState>>(emptyMap())
    val astronomyStates: LiveData<Map<String, AstronomyState>> = _astronomyStates

    private val lastRefreshMoments = mutableMapOf<String, Date>()

    fun getHomeState(city: SeedCity): HomeTimelineState {
        return _homeStates.value?.get(homeStateKey(city)) ?: HomeTimelineState()
    }

    fun getMonthState(city: SeedCity, month: Date): MonthScheduleState {
        return _monthStates.value?.get(monthStateKey(city, month)) ?: MonthScheduleState()
    }

    fun getAstronomyState(city: SeedCity, date: Date): AstronomyState {
        return _astronomyStates.value?.get(astronomyStateKey(city, date)) ?: AstronomyState()
    }

    suspend fun preloadInitial(baseURL: String, city: SeedCity, subscriptionStatus: String, language: AppLanguage) {
        val currentMonth = normalizedMonth(Date(), city.timezone)
        loadHome(baseURL, city, subscriptionStatus, language)
        loadAstronomy(baseURL, city, Date(), subscriptionStatus, language)
        loadCalendarMonth(baseURL, city, currentMonth)
    }

    @Suppress("UNUSED_PARAMETER")
    suspend fun loadHome(
        baseURL: String,
        city: SeedCity,
        subscriptionStatus: String,
        language: AppLanguage,
        forceRefresh: Boolean = false
    ) = withContext(Dispatchers.IO) {
        val stateKey = homeStateKey(city)
        val throttleKey = "network.home.$stateKey"
        val cacheKey = "cache.home.$stateKey"

        // Load cached if empty
        if (!getHomeState(city).hasContent) {
            val cachedEntry = cacheDao.getEntry(cacheKey)
            if (cachedEntry != null) {
                try {
                    val cachedResponse = gson.fromJson(cachedEntry.value, ChoghadiyaRangeResponse::class.java)
                    withContext(Dispatchers.Main) {
                        applyHome(cachedResponse, city, stateKey)
                    }
                } catch (e: Exception) {
                    // Ignore corrupted cache
                }
            }
        }

        // Throttle check
        if (shouldSkipRefresh(throttleKey, forceRefresh, 45) && getHomeState(city).hasContent) {
            return@withContext
        }

        withContext(Dispatchers.Main) {
            val currentMap = _homeStates.value ?: emptyMap()
            val state = currentMap[stateKey] ?: HomeTimelineState()
            if (!state.isLoading || forceRefresh) {
                state.isLoading = !state.hasContent
                state.isRefreshing = state.hasContent
                state.errorMessage = null
                _homeStates.value = currentMap + (stateKey to state)
            }
        }

        try {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, -1)
            val yesterday = calendar.time
            val response = apiClient.fetchRange(baseURL, city, yesterday, 3)
            
            // Save to Room cache
            cacheDao.insertEntry(CacheEntry(cacheKey, gson.toJson(response)))
            
            lastRefreshMoments[throttleKey] = Date()

            withContext(Dispatchers.Main) {
                applyHome(response, city, stateKey)
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                val currentMap = _homeStates.value ?: emptyMap()
                val state = currentMap[stateKey] ?: HomeTimelineState()
                state.isLoading = false
                state.isRefreshing = false
                state.errorMessage = e.message ?: e.toString()
                _homeStates.value = currentMap + (stateKey to state)
            }
        }
    }

    suspend fun loadCalendarMonth(
        baseURL: String,
        city: SeedCity,
        month: Date,
        forceRefresh: Boolean = false
    ) = withContext(Dispatchers.IO) {
        val normMonth = normalizedMonth(month, city.timezone)
        val stateKey = monthStateKey(city, normMonth)
        val throttleKey = "network.month.$stateKey"
        val cacheKey = "cache.month.$stateKey"

        // Load cached if empty
        if (!getMonthState(city, normMonth).hasContent) {
            val cachedEntry = cacheDao.getEntry(cacheKey)
            if (cachedEntry != null) {
                try {
                    val cachedResponse = gson.fromJson(cachedEntry.value, ChoghadiyaRangeResponse::class.java)
                    withContext(Dispatchers.Main) {
                        applyMonth(cachedResponse, stateKey)
                    }
                } catch (e: Exception) {
                    // Ignore corrupted
                }
            }
        }

        // Throttle check
        if (shouldSkipRefresh(throttleKey, forceRefresh, 60) && getMonthState(city, normMonth).hasContent) {
            return@withContext
        }

        withContext(Dispatchers.Main) {
            val currentMap = _monthStates.value ?: emptyMap()
            val state = currentMap[stateKey] ?: MonthScheduleState()
            if (!state.isLoading || forceRefresh) {
                state.isLoading = !state.hasContent
                state.isRefreshing = state.hasContent
                state.errorMessage = null
                _monthStates.value = currentMap + (stateKey to state)
            }
        }

        try {
            val cal = Calendar.getInstance(TimeZone.getTimeZone(city.timezone), Locale.US)
            cal.time = normMonth
            val dayCount = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
            val response = apiClient.fetchRange(baseURL, city, normMonth, dayCount)

            // Save to Room cache
            cacheDao.insertEntry(CacheEntry(cacheKey, gson.toJson(response)))
            
            lastRefreshMoments[throttleKey] = Date()

            withContext(Dispatchers.Main) {
                applyMonth(response, stateKey)
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                val currentMap = _monthStates.value ?: emptyMap()
                val state = currentMap[stateKey] ?: MonthScheduleState()
                state.isLoading = false
                state.isRefreshing = false
                state.errorMessage = e.message ?: e.toString()
                _monthStates.value = currentMap + (stateKey to state)
            }
        }
    }

    @Suppress("UNUSED_PARAMETER")
    suspend fun loadAstronomy(
        baseURL: String,
        city: SeedCity,
        date: Date,
        subscriptionStatus: String,
        language: AppLanguage,
        forceRefresh: Boolean = false
    ) = withContext(Dispatchers.IO) {
        val stateKey = astronomyStateKey(city, date)
        val throttleKey = "network.astronomy.$stateKey"
        val cacheKey = "cache.astronomy.$stateKey"

        // Load cached if empty
        if (!getAstronomyState(city, date).hasContent) {
            val cachedEntry = cacheDao.getEntry(cacheKey)
            if (cachedEntry != null) {
                try {
                    val cachedResponse = gson.fromJson(cachedEntry.value, SolarLunarDayResponse::class.java)
                    withContext(Dispatchers.Main) {
                        applyAstronomy(cachedResponse, stateKey)
                    }
                } catch (e: Exception) {
                    // Ignore corrupted
                }
            }
        }

        // Throttle check
        if (shouldSkipRefresh(throttleKey, forceRefresh, 90) && getAstronomyState(city, date).hasContent) {
            return@withContext
        }

        withContext(Dispatchers.Main) {
            val currentMap = _astronomyStates.value ?: emptyMap()
            val state = currentMap[stateKey] ?: AstronomyState()
            if (!state.isLoading || forceRefresh) {
                state.isLoading = !state.hasContent
                state.isRefreshing = state.hasContent
                state.errorMessage = null
                _astronomyStates.value = currentMap + (stateKey to state)
            }
        }

        try {
            val response = apiClient.fetchAstronomyDay(baseURL, city, date)

            // Save to Room cache
            cacheDao.insertEntry(CacheEntry(cacheKey, gson.toJson(response)))
            
            lastRefreshMoments[throttleKey] = Date()

            withContext(Dispatchers.Main) {
                applyAstronomy(response, stateKey)
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                val currentMap = _astronomyStates.value ?: emptyMap()
                val state = currentMap[stateKey] ?: AstronomyState()
                state.isLoading = false
                state.isRefreshing = false
                state.errorMessage = e.message ?: e.toString()
                _astronomyStates.value = currentMap + (stateKey to state)
            }
        }
    }

    private fun applyHome(response: ChoghadiyaRangeResponse, city: SeedCity, stateKey: String) {
        val now = Date()
        val visible = TimelineBuilder.visibleTimeline(response.days, city.timezone, now)
        val currentSlot = visible.firstOrNull { it.start.time <= now.time && now.time < it.end.time } ?: visible.firstOrNull()
        val nextSlots = if (visible.isNotEmpty()) visible.drop(1) else emptyList()
        val activeDate = currentSlot?.dayDate ?: response.days.firstOrNull()?.date
        val activeDay = response.days.firstOrNull { it.date == activeDate } ?: response.days.firstOrNull()

        val currentMap = _homeStates.value ?: emptyMap()
        val state = currentMap[stateKey] ?: HomeTimelineState()
        state.days = response.days
        state.currentSlot = currentSlot
        state.nextSlots = nextSlots
        state.provider = response.provider
        state.lastUpdated = parseISO8601(response.generatedAt)
        state.sunrise = activeDay?.sunrise ?: "--:--"
        state.sunset = activeDay?.sunset ?: "--:--"
        state.isLoading = false
        state.isRefreshing = false
        state.errorMessage = null
        _homeStates.value = currentMap + (stateKey to state)
    }

    private fun applyMonth(response: ChoghadiyaRangeResponse, stateKey: String) {
        val currentMap = _monthStates.value ?: emptyMap()
        val state = currentMap[stateKey] ?: MonthScheduleState()
        state.days = response.days
        state.provider = response.provider
        state.lastUpdated = parseISO8601(response.generatedAt)
        state.isLoading = false
        state.isRefreshing = false
        state.errorMessage = null
        _monthStates.value = currentMap + (stateKey to state)
    }

    private fun applyAstronomy(response: SolarLunarDayResponse, stateKey: String) {
        val currentMap = _astronomyStates.value ?: emptyMap()
        val state = currentMap[stateKey] ?: AstronomyState()
        state.day = response.day
        state.provider = response.provider
        state.lastUpdated = parseISO8601(response.generatedAt)
        state.isLoading = false
        state.isRefreshing = false
        state.errorMessage = null
        _astronomyStates.value = currentMap + (stateKey to state)
    }

    private fun shouldSkipRefresh(throttleKey: String, forceRefresh: Boolean, minimumIntervalSeconds: Int): Boolean {
        if (forceRefresh) return false
        val lastTime = lastRefreshMoments[throttleKey] ?: return false
        val diff = (Date().time - lastTime.time) / 1000
        return diff < minimumIntervalSeconds
    }

    private fun homeStateKey(city: SeedCity): String {
        return city.id
    }

    private fun monthStateKey(city: SeedCity, month: Date): String {
        val sdf = SimpleDateFormat("yyyy-MM", Locale.US).apply {
            timeZone = TimeZone.getTimeZone(city.timezone)
        }
        return "${city.id}|${sdf.format(month)}"
    }

    private fun astronomyStateKey(city: SeedCity, date: Date): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US).apply {
            timeZone = TimeZone.getTimeZone(city.timezone)
        }
        return "${city.id}|${sdf.format(date)}"
    }

    fun normalizedMonth(date: Date, timezone: String): Date {
        val cal = Calendar.getInstance(TimeZone.getTimeZone(timezone), Locale.US)
        cal.time = date
        cal.set(Calendar.DAY_OF_MONTH, 1)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.time
    }

    private fun parseISO8601(value: String): Date? {
        return try {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.US)
            sdf.parse(value)
        } catch (e: Exception) {
            try {
                val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.US)
                sdf.parse(value)
            } catch (e1: Exception) {
                Date()
            }
        }
    }
}
