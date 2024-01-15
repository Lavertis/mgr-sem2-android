package com.pollub.masterand.db.repositories.player_scores

import com.pollub.masterand.db.dao.PlayerScoreDao
import com.pollub.masterand.db.entities.PlayerWithScore
import kotlinx.coroutines.flow.Flow

class PlayerScoresRepositoryImpl(private val playerScoreDao: PlayerScoreDao) :
    PlayerScoresRepository {
    override fun loadPlayersWithScores(): Flow<List<PlayerWithScore>> =
        playerScoreDao.loadPlayersWithScores()
}