package com.techmeeva.chogadiyawidgets.models

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

data class ChoghadiyaLocation(
    val name: String,
    val lat: Double,
    val lng: Double,
    val timezone: String
)

data class ChoghadiyaSlot(
    val slot: Int,
    val phase: String,
    val type: String,
    @SerializedName("start_time") val startTime: String,
    @SerializedName("end_time") val endTime: String,
    @SerializedName("start_seconds") val startSeconds: Int,
    @SerializedName("end_seconds") val endSeconds: Int,
    @SerializedName("duration_minutes") val durationMinutes: Int
) {
    val id: String
        get() = "$slot-$phase-$type-$startSeconds"
}

data class ChoghadiyaDay(
    val date: String,
    val sunrise: String,
    val sunset: String,
    val slots: List<ChoghadiyaSlot>
)

data class ChoghadiyaDayResponse(
    val location: ChoghadiyaLocation,
    val provider: String,
    @SerializedName("generated_at") val generatedAt: String, // ISO-8601 String
    val day: ChoghadiyaDay
)

data class ChoghadiyaRangeResponse(
    val location: ChoghadiyaLocation,
    val provider: String,
    @SerializedName("generated_at") val generatedAt: String, // ISO-8601 String
    val days: List<ChoghadiyaDay>
)

data class TimingWindow(
    val key: String,
    @SerializedName("start_time") val startTime: String,
    @SerializedName("end_time") val endTime: String,
    val source: String
)

data class SolarLunarDay(
    val date: String,
    val sunrise: String,
    val sunset: String,
    @SerializedName("solar_noon") val solarNoon: String,
    val moonrise: String?,
    val moonset: String?,
    @SerializedName("moon_phase_key") val moonPhaseKey: String,
    @SerializedName("moon_phase_angle") val moonPhaseAngle: Double,
    @SerializedName("illumination_fraction") val illuminationFraction: Double,
    @SerializedName("auspicious_windows") val auspiciousWindows: List<TimingWindow>,
    @SerializedName("inauspicious_windows") val inauspiciousWindows: List<TimingWindow>
)

data class SolarLunarDayResponse(
    val location: ChoghadiyaLocation,
    val provider: String,
    @SerializedName("generated_at") val generatedAt: String, // ISO-8601 String
    val day: SolarLunarDay
)

data class TimelineSlot(
    val id: String,
    val dayDate: String,
    val type: String,
    val start: Date,
    val end: Date,
    val phase: String,
    val slotNumber: Int
) {
    val formattedRange: String
        get() {
            val formatter = SimpleDateFormat("h:mm a", Locale.US)
            return "${formatter.format(start)} - ${formatter.format(end)}"
        }
}
