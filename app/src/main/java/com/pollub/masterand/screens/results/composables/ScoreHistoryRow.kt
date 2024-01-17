package com.pollub.masterand.screens.results.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pollub.masterand.db.entities.PlayerWithScore

@Composable
fun ScoreHistoryRow(playerScore: PlayerWithScore) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = playerScore.playerName, style = MaterialTheme.typography.displaySmall)
        Text(text = playerScore.score.toString(), style = MaterialTheme.typography.displaySmall)
    }
}