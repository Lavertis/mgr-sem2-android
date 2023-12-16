package com.pollub.lab_4.screens.results

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pollub.lab_4.screens.results.composables.LogoutButton
import com.pollub.lab_4.screens.results.composables.PlayAgainButton
import com.pollub.lab_4.screens.results.composables.ResultsHeader
import com.pollub.lab_4.screens.results.composables.ResultsSummary
import com.pollub.lab_4.ui.theme.Lab_4Theme


@Composable
fun ResultsScreen(attemptCount: Int, onLogoutButtonClicked: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        ResultsHeader()
        Spacer(modifier = Modifier.height(16.dp))
        ResultsSummary(attemptCount = attemptCount)
        PlayAgainButton()
        LogoutButton(onLogoutButtonClicked = onLogoutButtonClicked)
    }
}


@Preview(showBackground = true)
@Composable
fun ResultsScreenPreview() {
    Lab_4Theme {
        ResultsScreen(5) {}
    }
}