package com.example.horseracingapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HorseRaceResultEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun raceDao(): RaceDao
}