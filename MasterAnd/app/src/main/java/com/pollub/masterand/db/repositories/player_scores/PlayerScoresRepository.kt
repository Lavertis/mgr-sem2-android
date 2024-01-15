package com.pollub.masterand.db.repositories.player_scores

import com.pollub.masterand.db.entities.PlayerWithScore
import kotlinx.coroutines.flow.Flow

interface PlayerScoresRepository {
    fun loadPlayersWithScores(): Flow<List<PlayerWithScore>>
}