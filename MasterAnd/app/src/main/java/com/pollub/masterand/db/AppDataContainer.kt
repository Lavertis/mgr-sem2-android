package com.pollub.masterand.db

import android.content.Context
import com.pollub.masterand.db.repositories.PlayersRepository
import com.pollub.masterand.db.repositories.PlayersRepositoryImpl

class AppDataContainer(private val context: Context) : AppContainer {
    override val playersRepository: PlayersRepository by lazy {
        PlayersRepositoryImpl(HighScoreDatabase.getDatabase(context).playerDao())
    }
}