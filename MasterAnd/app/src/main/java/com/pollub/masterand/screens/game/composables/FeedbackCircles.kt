package com.pollub.masterand.screens.game.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FeedbackCircles(colors: List<Color>) {
    val spacedBy = 2.dp
    Column(verticalArrangement = Arrangement.spacedBy(spacedBy)) {
        colors.chunked(2).forEach { rowColors ->
            Row(horizontalArrangement = Arrangement.spacedBy(spacedBy)) {
                rowColors.forEach { color ->
                    SmallCircle(color = color, modifier = Modifier.size(24.dp))
                }
            }
        }
    }
}