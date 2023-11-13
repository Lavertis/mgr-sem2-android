package com.pollub.lab_3

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

class GameRowState {
    val selectedColors = mutableStateListOf(Color.White, Color.White, Color.White, Color.White)
    var feedbackColors = mutableStateListOf(Color.White, Color.White, Color.White, Color.White)
    val clickable = mutableStateOf(true)
}