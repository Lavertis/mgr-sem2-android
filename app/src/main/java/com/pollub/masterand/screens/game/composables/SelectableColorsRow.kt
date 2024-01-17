package com.pollub.masterand.screens.game.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SelectableColorsRow(colors: List<Color>, clickAction: (Int) -> Unit = {}) {
    val animatedColors = colors.map {
        animateColorAsState(
            targetValue = it,
            animationSpec = repeatable(
                iterations = 5,
                animation = tween(durationMillis = 500),
                repeatMode = RepeatMode.Reverse
            ),
            label = "Color_{$it}"
        )
    }

    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        animatedColors.forEachIndexed { index, animatedColor ->
            CircularButton(
                onClick = { clickAction.invoke(index) },
                color = animatedColor.value,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}