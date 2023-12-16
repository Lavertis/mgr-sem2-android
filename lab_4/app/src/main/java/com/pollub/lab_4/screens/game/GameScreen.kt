package com.pollub.lab_4.screens.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
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
import com.pollub.lab_4.screens.game.composables.GameRow
import com.pollub.lab_4.screens.game.constants.CIRCLE_COLORS
import com.pollub.lab_4.screens.game.models.GameRowState
import com.pollub.lab_4.screens.game.utils.checkColors
import com.pollub.lab_4.screens.game.utils.selectNextAvailableColor
import com.pollub.lab_4.screens.game.utils.selectRandomColors
import com.pollub.lab_4.ui.theme.Lab_4Theme
import kotlinx.coroutines.launch


@Composable
fun GameScreen(
    colorCount: Int,
    onBackButtonClicked: () -> Unit,
    onGoToResultsScreenButtonClicked: (attemptCount: Int) -> Unit
) {
    val allColors = CIRCLE_COLORS.take(colorCount)
    val trueColors = remember { mutableStateOf(selectRandomColors(allColors)) }
    val gameRowStates = remember { mutableStateListOf(GameRowState()) }
    val isGameFinished = remember { mutableStateOf(true) } // TODO: change to false

    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
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
                    onSelectColorClick = {
                        gameRowStates[index].selectedColors[it] =
                            selectNextAvailableColor(
                                allColors,
                                gameRowStates[index].selectedColors,
                                gameRowStates[index].selectedColors[it]
                            )
                    },
                    onCheckClick = {
                        gameRowStates[index].feedbackColors = checkColors(
                            trueColors = trueColors.value,
                            selectedColors = gameRowStates[index].selectedColors
                        )
                        gameRowStates[index].clickable.value = false

                        if (gameRowStates[index].feedbackColors.all { it == Color.Red }) {
                            isGameFinished.value = true
                        } else {
                            gameRowStates.add(GameRowState())
                            coroutineScope.launch {
                                lazyListState.scrollToItem(gameRowStates.size - 1)
                            }
                        }
                    }
                )
            }
        }
        if (isGameFinished.value) {
            Row {
                Button(
                    onClick = {
                        onGoToResultsScreenButtonClicked(gameRowStates.size)
                    }
                ) {
                    Text(text = "High score table")
                }
                Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                Button(
                    onClick = {
//                    gameRowStates.clear()
//                    gameRowStates.add(GameRowState())
//                    isGameFinished.value = false
//                    trueColors.value = selectRandomColors(allColors)
                        onBackButtonClicked()
                    },
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    Text(text = "Logout")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    Lab_4Theme {
        GameScreen(
            colorCount = 5,
            onBackButtonClicked = {},
            onGoToResultsScreenButtonClicked = {}
        )
    }
}