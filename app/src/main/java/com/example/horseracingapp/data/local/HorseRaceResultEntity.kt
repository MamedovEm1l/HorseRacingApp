package com.example.horseracingapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "race_results")
data class HorseRaceResultEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val winner: String,
    val duration: Long,
    val timestamp: Long
)