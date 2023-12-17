package com.pollub.masterand.screens.game.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SelectableColorsRow(colors: List<Color>, clickAction: ((Int) -> Unit)?) {
    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        colors.forEachIndexed { index, color ->
            CircularButton(
                onClick = { clickAction?.invoke(index) },
                color = color,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}