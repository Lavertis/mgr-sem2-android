package com.pollub.masterand.db

import com.pollub.masterand.db.repositories.players.PlayersRepository
import com.pollub.masterand.db.repositories.scores.ScoresRepository

interface AppContainer {
    val playersRepository: PlayersRepository
    val scoresRepository: ScoresRepository
}