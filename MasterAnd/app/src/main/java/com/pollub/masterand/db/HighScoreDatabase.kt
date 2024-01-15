package com.pollub.masterand.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pollub.masterand.db.dao.PlayerDao
import com.pollub.masterand.db.dao.PlayerScoreDao
import com.pollub.masterand.db.entities.Player
import com.pollub.masterand.db.entities.Score

@Database(entities = [Player::class, Score::class], version = 1, exportSchema = false)
abstract class HighScoreDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
    abstract fun playerScoreDao(): PlayerScoreDao

    companion object {
        @Volatile
        private var Instance: HighScoreDatabase? = null

        fun getDatabase(context: Context): HighScoreDatabase {
            return Room.databaseBuilder(
                context,
                HighScoreDatabase::class.java,
                "highscore_database"
            ).build().also { Instance = it }
        }
    }
}