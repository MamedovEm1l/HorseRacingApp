package com.example.horseracingapp.data.repository

import com.example.horseracingapp.data.local.HorseRaceResultEntity
import com.example.horseracingapp.data.local.RaceDao
import com.example.horseracingapp.data.model.HorseRaceResult
import javax.inject.Inject

class RaceRepositoryImpl @Inject constructor(
    private val raceDao: RaceDao
) : RaceRepository {

    override suspend fun saveRaceResult(result: HorseRaceResult) {
        val entity = HorseRaceResultEntity(
            winner = result.winner,
            duration = result.duration,
            timestamp = result.timestamp
        )
        raceDao.insert(entity)
    }

    override suspend fun getRaceHistory(): List<HorseRaceResult> {
        return raceDao.getAll().map {
            HorseRaceResult(
                winner = it.winner,
                duration = it.duration,
                timestamp = it.timestamp
            )
        }
    }

    override suspend fun clearHistory() {
        raceDao.clearAll()
    }
}
