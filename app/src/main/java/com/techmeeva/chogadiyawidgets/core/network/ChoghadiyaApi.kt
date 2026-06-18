package com.techmeeva.chogadiyawidgets.core.network

import com.techmeeva.chogadiyawidgets.models.ChoghadiyaDayResponse
import com.techmeeva.chogadiyawidgets.models.ChoghadiyaRangeResponse
import com.techmeeva.chogadiyawidgets.models.SolarLunarDayResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ChoghadiyaApi {

    @GET("v1/choghadiya/day")
    suspend fun fetchDay(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
        @Query("date") date: String
    ): ChoghadiyaDayResponse

    @GET("v1/choghadiya/range")
    suspend fun fetchRange(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
        @Query("start_date") startDate: String,
        @Query("days") days: Int
    ): ChoghadiyaRangeResponse

    @GET("v1/astronomy/day")
    suspend fun fetchAstronomyDay(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
        @Query("date") date: String
    ): SolarLunarDayResponse
}
