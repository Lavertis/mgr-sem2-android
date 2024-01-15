package com.pollub.masterand.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pollub.masterand.db.entities.Player

@Dao
interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(player: Player): Long

    @Update
    suspend fun update(player: Player): Int

    @Query("SELECT * FROM players WHERE id = :id")
    suspend fun getPlayersById(id: Long): List<Player>

//    @Query("SELECT * FROM players WHERE id = :id")
//    fun getPlayerStream(id: Int): Flow<Player>
//
//    @Query("SELECT * FROM players")
//    fun getAllPlayersStream(): Flow<List<Player>>

    @Query("SELECT * FROM players WHERE email = :email")
    suspend fun getPlayersByEmail(email: String): List<Player>
}