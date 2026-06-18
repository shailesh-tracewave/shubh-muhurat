package com.techmeeva.chogadiyawidgets.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "response_cache")
data class CacheEntry(
    @PrimaryKey val key: String,
    val value: String, // Serialized JSON response
    val timestamp: Long = System.currentTimeMillis()
)
