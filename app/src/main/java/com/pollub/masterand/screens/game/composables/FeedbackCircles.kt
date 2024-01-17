package com.pollub.masterand.screens.game.composables

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FeedbackCircles(colors: List<Color>) {
    val spacedBy = 2.dp

    val colorAnimations = List(4) { remember { Animatable(Color.White) } }
    LaunchedEffect(colors) {
        colorAnimations.forEachIndexed { index, animation ->
            animation.animateTo(colors[index], animationSpec = tween(250))
        }
    }

    Column(verticalArrangement = Arrangement.spacedBy(spacedBy)) {
        colorAnimations.chunked(2).forEach { rowColorAnimations ->
            Row(horizontalArrangement = Arrangement.spacedBy(spacedBy)) {
                rowColorAnimations.forEach { colorAnimation ->
                    SmallCircle(color = colorAnimation.value, modifier = Modifier.size(24.dp))
                }
            }
        }
    }
}