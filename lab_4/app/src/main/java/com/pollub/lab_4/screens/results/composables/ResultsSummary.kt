package com.pollub.lab_4.screens.results.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ResultsSummary(attemptCount: Int) {
    Text(
        text = "Recent attempts: $attemptCount",
        style = MaterialTheme.typography.displayMedium
    )
}