package com.pollub.lab_4.screens.game.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color


data class GameRowState(
    val selectedColors: SnapshotStateList<Color> = mutableStateListOf(
        Color.White, Color.White, Color.White, Color.White
    ),
    var feedbackColors: SnapshotStateList<Color> = mutableStateListOf(
        Color.White, Color.White, Color.White, Color.White
    ),
    val clickable: MutableState<Boolean> = mutableStateOf(true)
)
