package com.pollub.masterand.screens.results

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.lifecycle.viewmodel.compose.viewModel
import com.pollub.masterand.db.entities.PlayerWithScore
//import com.pollub.masterand.providers.AppViewModelProvider
import com.pollub.masterand.screens.results.composables.LogoutButton
import com.pollub.masterand.screens.results.composables.PlayAgainButton
import com.pollub.masterand.screens.results.composables.ResultsHeader
import com.pollub.masterand.screens.results.composables.ResultsSummary
import com.pollub.masterand.screens.results.composables.ScoreHistory
import com.pollub.masterand.ui.theme.MasterAndTheme
import com.pollub.masterand.view_models.ResultsViewModel


@Composable
fun ResultsScreen(
    attemptCount: Int,
    navigateToGameScreen: () -> Unit,
    navigateToProfileScreen: () -> Unit,
    viewModel: ResultsViewModel = hiltViewModel<ResultsViewModel>()
//    viewModel: ResultsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val playersFlow = viewModel.loadPlayerScores()
    var playerScores by remember { mutableStateOf(emptyList<PlayerWithScore>()) }
    LaunchedEffect(playersFlow) {
        playersFlow.collect { newPlayers ->
            playerScores = newPlayers
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        ResultsHeader()
        Spacer(modifier = Modifier.height(12.dp))
        ResultsSummary(attemptCount = attemptCount)
        ScoreHistory(playerScores = playerScores)
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