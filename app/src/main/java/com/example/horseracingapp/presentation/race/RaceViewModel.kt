package com.example.horseracingapp.presentation.race

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horseracingapp.data.model.HorseRaceResult
import com.example.horseracingapp.data.repository.RaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RaceViewModel @Inject constructor(
    private val repository: RaceRepository
) : ViewModel() {
    val horses = listOf("Быстрый Ветер", "Черный Пламя", "Золотой Гром", "Серебряный Вихрь")
    var raceInProgress by mutableStateOf(false)
    var winner by mutableStateOf<String?>(null)
    var raceTime by mutableLongStateOf(0L)
    var horsePositions by mutableStateOf(horses.map { 0 })

    fun startRace() {
        viewModelScope.launch {
            raceInProgress = true
            winner = null
            horsePositions = horses.map { 0 }
            raceTime = 0L

            val finishLineX = 300
            val raceDurationLimit = 7000L
            val startTime = System.currentTimeMillis()

            var finished = BooleanArray(horses.size) { false }
            var winnerTime: Long? = null

            while (System.currentTimeMillis() - startTime < raceDurationLimit) {
                horsePositions = horsePositions.mapIndexed { index, pos ->
                    if (finished[index]) {
                        pos
                    } else {
                        val newPos = pos + (1..5).random() * (if (index % 2 == 0) 1 else 2)
                        if (newPos >= finishLineX && !finished[index]) {
                            finished[index] = true

                            if (winner == null) {
                                winner = horses[index]
                                winnerTime = System.currentTimeMillis() - startTime
                            }
                        }
                        newPos
                    }
                }

                val allFinished = finished.all { it }
                raceTime = winnerTime ?: (System.currentTimeMillis() - startTime)

                if (allFinished) break
                delay(16)
            }

            raceInProgress = false

            if (winner == null) {
                val maxPos = horsePositions.maxOrNull() ?: 0
                val winnerIndex = horsePositions.indexOf(maxPos)
                winner = horses[winnerIndex]
                winnerTime = System.currentTimeMillis() - startTime
                raceTime = winnerTime
            }

            val result = HorseRaceResult(
                winner = winner!!,
                duration = raceTime,
                timestamp = System.currentTimeMillis()
            )
            repository.saveRaceResult(result)
        }
    }

    fun resetRace() {
        winner = null
        horsePositions = horses.map { 0 }
        raceTime = 0L
    }
}

