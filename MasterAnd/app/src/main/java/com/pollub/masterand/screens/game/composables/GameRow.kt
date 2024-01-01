package com.pollub.masterand.screens.game.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GameRow(
    selectedColors: List<Color>,
    feedbackColors: List<Color>,
    clickable: Boolean,
    onSelectColorClick: (Int) -> Unit,
    onCheckClick: () -> Unit
) {
    val isButtonEnabled = clickable && !selectedColors.contains(Color.White)
    val visibleState = remember { MutableTransitionState(false) }
    if (visibleState.targetState != isButtonEnabled)
        visibleState.targetState = isButtonEnabled

    Row {
        SelectableColorsRow(
            colors = selectedColors,
            clickAction = if (clickable) onSelectColorClick else { _ -> }
        )
        Spacer(modifier = Modifier.padding(horizontal = 2.5.dp))
        AnimatedVisibility(
            visibleState = visibleState,
            enter = scaleIn(animationSpec = tween(1000)),
            exit = scaleOut(animationSpec = tween(1000))
        ) {
            IconButton(
                onClick = onCheckClick,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                colors = IconButtonDefaults.filledIconButtonColors(),
                enabled = clickable && !selectedColors.contains(Color.White)
            ) {
                Icon(Icons.Default.Check, contentDescription = null, tint = Color.White)
            }
        }
        if (visibleState.isIdle && !visibleState.currentState) {
            Box(modifier = Modifier.size(50.dp))
        }
        Spacer(modifier = Modifier.padding(horizontal = 2.5.dp))
        FeedbackCircles(colors = feedbackColors)
    }
}