package com.pollub.masterand.db

import android.content.Context
import com.pollub.masterand.db.repositories.players.PlayersRepository
import com.pollub.masterand.db.repositories.players.PlayersRepositoryImpl
import com.pollub.masterand.db.repositories.scores.ScoresRepository
import com.pollub.masterand.db.repositories.scores.ScoresRepositoryImpl

class AppDataContainer(private val context: Context) : AppContainer {
    override val playersRepository: PlayersRepository by lazy {
        PlayersRepositoryImpl(HighScoreDatabase.getDatabase(context).playerDao())
    }
    override val scoresRepository: ScoresRepository by lazy {
        ScoresRepositoryImpl(HighScoreDatabase.getDatabase(context).scoreDao())
    }
}