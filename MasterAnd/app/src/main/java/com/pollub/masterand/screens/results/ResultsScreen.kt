package com.pollub.masterand.screens.results

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
import com.pollub.masterand.screens.results.composables.LogoutButton
import com.pollub.masterand.screens.results.composables.PlayAgainButton
import com.pollub.masterand.screens.results.composables.ResultsHeader
import com.pollub.masterand.screens.results.composables.ResultsSummary
import com.pollub.masterand.ui.theme.MasterAndTheme


@Composable
fun ResultsScreen(
    attemptCount: Int,
    navigateToGameScreen: () -> Unit,
    navigateToProfileScreen: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        ResultsHeader()
        Spacer(modifier = Modifier.height(16.dp))
        ResultsSummary(attemptCount = attemptCount)
        PlayAgainButton(onClick = navigateToGameScreen)
        LogoutButton(onClick = navigateToProfileScreen)
    }
}


@Preview(showBackground = true)
@Composable
fun ResultsScreenPreview() {
    MasterAndTheme {
        ResultsScreen(attemptCount = 5, navigateToGameScreen = {}, navigateToProfileScreen = {})
    }
}