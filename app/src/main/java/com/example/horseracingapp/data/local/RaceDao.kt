package com.example.horseracingapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RaceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(result: HorseRaceResultEntity)

    @Query("SELECT * FROM race_results ORDER BY timestamp DESC")
    suspend fun getAll(): List<HorseRaceResultEntity>

    @Query("DELETE FROM race_results")
    suspend fun clearAll()
}
