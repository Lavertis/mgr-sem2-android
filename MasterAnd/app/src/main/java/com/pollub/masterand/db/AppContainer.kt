package com.pollub.masterand.db

import com.pollub.masterand.db.repositories.PlayersRepository

interface AppContainer {
    val playersRepository: PlayersRepository
}