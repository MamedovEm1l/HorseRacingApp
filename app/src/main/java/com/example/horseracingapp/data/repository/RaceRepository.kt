package com.example.horseracingapp.data.repository

import com.example.horseracingapp.data.model.HorseRaceResult

interface RaceRepository {
    suspend fun saveRaceResult(result: HorseRaceResult)
    suspend fun getRaceHistory(): List<HorseRaceResult>
    suspend fun clearHistory()
}