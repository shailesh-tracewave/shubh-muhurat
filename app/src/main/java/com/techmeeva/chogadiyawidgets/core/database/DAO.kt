package com.techmeeva.chogadiyawidgets.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CacheDao {

    @Query("SELECT * FROM response_cache WHERE `key` = :key LIMIT 1")
    suspend fun getEntry(key: String): CacheEntry?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: CacheEntry)

    @Query("DELETE FROM response_cache WHERE `key` = :key")
    suspend fun deleteEntry(key: String)

    @Query("DELETE FROM response_cache")
    suspend fun clearAll()
}
