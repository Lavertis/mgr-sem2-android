package com.pollub.masterand.screens.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pollub.masterand.providers.AppViewModelProvider
import com.pollub.masterand.screens.game.composables.EndGameButtons
import com.pollub.masterand.screens.game.composables.GameRow
import com.pollub.masterand.screens.game.constants.CIRCLE_COLORS
import com.pollub.masterand.screens.game.models.GameRowState
import com.pollub.masterand.screens.game.utils.checkColors
import com.pollub.masterand.screens.game.utils.selectNextAvailableColor
import com.pollub.masterand.screens.game.utils.selectRandomColors
import com.pollub.masterand.ui.theme.MasterAndTheme
import com.pollub.masterand.view_models.GameViewModel
import kotlinx.coroutines.launch


@Composable
fun GameScreen(
    colorCount: Int,
    navigateToProfileScreen: () -> Unit,
    navigateToResultsScreen: (attemptCount: Int) -> Unit,
    viewModel: GameViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val allColors = CIRCLE_COLORS.take(colorCount)
    val trueColors = remember { mutableStateOf(selectRandomColors(allColors)) }
    val gameRowStates = remember { mutableStateListOf(GameRowState()) }
    val isGameFinished = remember { mutableStateOf(true) } // TODO: change to false

    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val onSelectColorClick: (Int) -> Unit = { index ->
        gameRowStates.last().selectedColors[index] = selectNextAvailableColor(
            colors = allColors,
            selectedColors = gameRowStates.last().selectedColors,
            currentColor = gameRowStates.last().selectedColors[index]
        )
    }
    val onCheckClick: () -> Unit = {
        gameRowStates.last().feedbackColors = checkColors(
            trueColors = trueColors.value,
            selectedColors = gameRowStates.last().selectedColors
        )
        gameRowStates.last().clickable.value = false

        if (gameRowStates.last().feedbackColors.all { it == Color.Red }) {
            isGameFinished.value = true
        } else {
            gameRowStates.add(GameRowState())
            coroutineScope.launch {
                lazyListState.scrollToItem(gameRowStates.size - 1)
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Attempts: ${gameRowStates.size}",
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier.padding(bottom = 10.dp)
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .padding(bottom = 10.dp)
                .weight(1f),
            state = lazyListState
        ) {
            items(gameRowStates.size) { index ->
                GameRow(
                    selectedColors = gameRowStates[index].selectedColors,
                    feedbackColors = gameRowStates[index].feedbackColors,
                    clickable = gameRowStates[index].clickable.value,
                    onSelectColorClick = { onSelectColorClick(it) },
                    onCheckClick = onCheckClick
                )
            }
        }
        if (isGameFinished.value) {
            EndGameButtons(
                onHighScoreTableButtonClick = {
                    viewModel.score.longValue = gameRowStates.size.toLong()
                    coroutineScope.launch {
                        viewModel.savePlayerScore()
                    }
                    navigateToResultsScreen(gameRowStates.size)
                },
                onLogoutButtonClick = navigateToProfileScreen
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    MasterAndTheme {
        GameScreen(colorCount = 5, navigateToProfileScreen = {}, navigateToResultsScreen = {})
    }
}