package com.pollub.masterand.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.pollub.masterand.db.entities.PlayerWithScore
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerScoreDao {
    @Query(
        "SELECT " +
                "players.id AS playerId," +
                "players.name AS playerName," +
                "scores.id AS scoreId, " +
                "scores.score AS score " +
                "FROM players, scores WHERE players.id = scores.playerId"
    )
    fun loadPlayersWithScores(): Flow<List<PlayerWithScore>>
}