package com.pollub.masterand.view_models

import androidx.lifecycle.ViewModel
import com.pollub.masterand.db.entities.PlayerWithScore
import com.pollub.masterand.db.repositories.player_scores.PlayerScoresRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(val playerScoresRepository: PlayerScoresRepository) : ViewModel() {

    fun loadPlayerScores(): Flow<List<PlayerWithScore>> {
        return playerScoresRepository.loadPlayersWithScores()
    }
}