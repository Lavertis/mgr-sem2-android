package com.pollub.masterand.db.repositories.player_scores

import com.pollub.masterand.db.dao.PlayerScoreDao
import com.pollub.masterand.db.entities.PlayerWithScore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerScoresRepositoryImpl @Inject constructor(private val playerScoreDao: PlayerScoreDao) :
    PlayerScoresRepository {
    override fun loadPlayersWithScores(): Flow<List<PlayerWithScore>> =
        playerScoreDao.loadPlayersWithScores()
}