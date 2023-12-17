package com.pollub.masterand.screens.game.utils

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color

fun checkColors(
    trueColors: List<Color>,
    selectedColors: List<Color>
): SnapshotStateList<Color> {
    val feedbackColors = mutableStateListOf<Color>()
    for (i in trueColors.indices) {
        if (trueColors[i] == selectedColors[i]) {
            feedbackColors.add(Color.Red)
        } else if (trueColors.contains(selectedColors[i])) {
            feedbackColors.add(Color.Yellow)
        } else {
            feedbackColors.add(Color.White)
        }
    }
    return feedbackColors
}