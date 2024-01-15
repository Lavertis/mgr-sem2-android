package com.pollub.masterand.db.repositories

import androidx.lifecycle.LiveData
import com.pollub.masterand.db.entities.Player

interface PlayersRepository {
    fun getCurrentPlayerId(): LiveData<Int>

    fun setCurrentPlayerId(id: Int)
    suspend fun getPlayersByEmail(email: String): List<Player>
    suspend fun getPlayersById(id: Long): List<Player>
    suspend fun insert(player: Player): Long
    suspend fun update(player: Player): Int
}