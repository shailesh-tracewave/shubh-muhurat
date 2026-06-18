package com.techmeeva.chogadiyawidgets.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class SeedCity(
    val name: String,
    val city: String,
    val state: String,
    val country: String,
    val lat: Double,
    val lng: Double,
    val timezone: String
) {
    val id: String
        get() = "$city-$state-$country"

    companion object {
        val ahmedabadFallback = SeedCity(
            name = "Ahmedabad, Gujarat, India",
            city = "Ahmedabad",
            state = "Gujarat",
            country = "India",
            lat = 23.0225,
            lng = 72.5714,
            timezone = "Asia/Kolkata"
        )

        fun loadFromAssets(context: Context): List<SeedCity> {
            return try {
                val jsonString = context.assets.open("SeedCities.json").bufferedReader().use { it.readText() }
                val listType = object : TypeToken<List<SeedCity>>() {}.type
                Gson().fromJson(jsonString, listType)
            } catch (e: Exception) {
                listOf(ahmedabadFallback)
            }
        }
    }
}
