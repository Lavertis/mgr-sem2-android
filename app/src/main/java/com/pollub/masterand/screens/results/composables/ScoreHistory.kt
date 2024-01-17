package com.pollub.masterand.screens.results.composables

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pollub.masterand.db.entities.PlayerWithScore

@Composable
fun ScoreHistory(playerScores: List<PlayerWithScore>) {
    LazyColumn(
        modifier = Modifier
            .height(450.dp)
            .padding(16.dp)
    ) {
        items(playerScores) { playerScore ->
            ScoreHistoryRow(playerScore = playerScore)
            Divider(color = Color.Gray, thickness = 2.dp)
        }
    }
}