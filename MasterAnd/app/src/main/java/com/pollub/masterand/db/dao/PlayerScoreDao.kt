package com.pollub.masterand.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.pollub.masterand.db.entities.PlayerWithScore

@Dao
interface PlayerScoreDao {
    @Query(
        "SELECT players.id AS playerId, scores.id AS scoreId " +
                "FROM players, scores WHERE players.id = scores.playerId"
    )
    fun loadPlayersWithScores(): List<PlayerWithScore>
}