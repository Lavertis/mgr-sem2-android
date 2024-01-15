package com.pollub.masterand.view_models

import androidx.lifecycle.ViewModel
import com.pollub.masterand.db.entities.PlayerWithScore
import com.pollub.masterand.db.repositories.player_scores.PlayerScoresRepository
import kotlinx.coroutines.flow.Flow

class ResultsViewModel(val playerScoresRepository: PlayerScoresRepository) : ViewModel() {

    fun loadPlayerScores(): Flow<List<PlayerWithScore>> {
        return playerScoresRepository.loadPlayersWithScores()
    }
}