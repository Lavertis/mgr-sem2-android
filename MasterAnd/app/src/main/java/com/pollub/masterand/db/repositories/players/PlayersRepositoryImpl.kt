package com.pollub.masterand.db.repositories.players

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pollub.masterand.db.dao.PlayerDao
import com.pollub.masterand.db.entities.Player

class PlayersRepositoryImpl(private val playerDao: PlayerDao) : PlayersRepository {
    private val currentPlayerId = MutableLiveData<Long>()

    override fun getCurrentPlayerId(): LiveData<Long> = currentPlayerId

    override fun setCurrentPlayerId(id: Long) = currentPlayerId.postValue(id)

    override suspend fun getPlayersByEmail(email: String): List<Player> =
        playerDao.getPlayersByEmail(email)

    override suspend fun getPlayersById(id: Long): List<Player> = playerDao.getPlayersById(id)

    override suspend fun insert(player: Player): Long = playerDao.insert(player)
    override suspend fun update(player: Player): Int = playerDao.update(player)
}