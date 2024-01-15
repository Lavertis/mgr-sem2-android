package com.pollub.masterand.containers

import com.pollub.masterand.db.repositories.player_scores.PlayerScoresRepository
import com.pollub.masterand.db.repositories.players.PlayersRepository
import com.pollub.masterand.db.repositories.scores.ScoresRepository

interface AppContainer {
    val playersRepository: PlayersRepository
    val scoresRepository: ScoresRepository
    val playerScoresRepository: PlayerScoresRepository
}