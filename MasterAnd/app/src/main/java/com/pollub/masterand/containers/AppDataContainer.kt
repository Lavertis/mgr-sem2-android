package com.pollub.masterand.containers

//import android.content.Context
//import com.pollub.masterand.db.HighScoreDatabase
//import com.pollub.masterand.db.repositories.player_scores.PlayerScoresRepository
//import com.pollub.masterand.db.repositories.player_scores.PlayerScoresRepositoryImpl
//import com.pollub.masterand.db.repositories.players.PlayersRepository
//import com.pollub.masterand.db.repositories.players.PlayersRepositoryImpl
//import com.pollub.masterand.db.repositories.scores.ScoresRepository
//import com.pollub.masterand.db.repositories.scores.ScoresRepositoryImpl
//
//class AppDataContainer(private val context: Context) : AppContainer {
//    override val playersRepository: PlayersRepository by lazy {
//        PlayersRepositoryImpl(HighScoreDatabase.getDatabase(context).playerDao())
//    }
//    override val scoresRepository: ScoresRepository by lazy {
//        ScoresRepositoryImpl(HighScoreDatabase.getDatabase(context).scoreDao())
//    }
//    override val playerScoresRepository: PlayerScoresRepository by lazy {
//        PlayerScoresRepositoryImpl(HighScoreDatabase.getDatabase(context).playerScoreDao())
//    }
//}