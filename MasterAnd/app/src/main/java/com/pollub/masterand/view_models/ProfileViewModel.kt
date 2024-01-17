package com.pollub.masterand.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.pollub.masterand.db.entities.Player
import com.pollub.masterand.db.repositories.players.PlayersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private var playersRepository: PlayersRepository) : ViewModel() {
    var id = mutableStateOf(0L)
    val name = mutableStateOf("")
    val email = mutableStateOf("")

    suspend fun savePlayer() {
        val players = playersRepository.getPlayersByEmail(email.value)
        var player: Player
        if (players.isEmpty()) {
            player = Player(
                name = name.value,
                email = email.value
            )
            val playerId = playersRepository.insert(player)
            player = playersRepository.getPlayersById(playerId).first()
        } else {
            player = players.first()
            player.name = name.value
            playersRepository.update(player)
        }
        playersRepository.setCurrentPlayerId(player.id)
    }
}