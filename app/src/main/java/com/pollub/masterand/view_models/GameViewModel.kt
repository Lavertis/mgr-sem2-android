package com.pollub.masterand.view_models

import androidx.compose.runtime.mutableLongStateOf
import androidx.lifecycle.ViewModel
import com.pollub.masterand.db.entities.Score
import com.pollub.masterand.db.repositories.players.PlayersRepository
import com.pollub.masterand.db.repositories.scores.ScoresRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    playersRepository: PlayersRepository,
    private val scoresRepository: ScoresRepository
) : ViewModel() {
    var playerId = playersRepository.getCurrentPlayerId()
    var score = mutableLongStateOf(0L)

    suspend fun savePlayerScore() {
        val playerIdValue = playerId.value ?: throw IllegalStateException("PlayerId is null")
        val score = Score(playerId = playerIdValue, score = score.longValue)
        scoresRepository.insert(score)
    }
}