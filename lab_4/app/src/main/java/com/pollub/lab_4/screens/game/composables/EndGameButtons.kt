package com.pollub.lab_4.screens.game.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EndGameButtons(
    onHighScoreTableButtonClick: () -> Unit,
    onLogoutButtonClick: () -> Unit
) {
    Row {
        Button(onClick = onHighScoreTableButtonClick) {
            Text(text = "High score table")
        }
        Spacer(modifier = Modifier.padding(horizontal = 10.dp))
        Button(
            onClick = onLogoutButtonClick,
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            Text(text = "Logout")
        }
    }
}