package com.pollub.masterand.screens.game.utils

import androidx.compose.ui.graphics.Color

fun selectRandomColors(colors: List<Color>): List<Color> {
    return colors.shuffled().take(4)
}