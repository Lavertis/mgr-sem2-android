package com.pollub.lab_3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pollub.lab_3.ui.theme.Lab_3Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab_3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameScreen()
                }
            }
        }
    }
}

fun selectRandomColors(colors: List<Color>): List<Color> {
    return colors.shuffled().take(4)
}

fun selectNextAvailableColor(
    colors: List<Color>,
    selectedColors: List<Color>,
    currentColor: Color
): Color {
    val currentIndex = colors.indexOf(currentColor)
    for (i in currentIndex + 1 until colors.size) {
        if (!selectedColors.contains(colors[i])) {
            return colors[i]
        }
    }
    for (i in 0 until currentIndex) {
        if (!selectedColors.contains(colors[i])) {
            return colors[i]
        }
    }
    throw IllegalStateException("No color available")
}

fun checkColors(
    trueColors: List<Color>,
    selectedColors: List<Color>
): SnapshotStateList<Color> {
    val feedbackColors = mutableStateListOf<Color>()
    for (i in trueColors.indices) {
        if (trueColors[i] == selectedColors[i]) {
            feedbackColors.add(Color.Red)
        } else if (trueColors.contains(selectedColors[i])) {
            feedbackColors.add(Color.Yellow)
        } else {
            feedbackColors.add(Color.White)
        }
    }
    return feedbackColors
}

@Composable
fun GameScreen() {
    val allColors = listOf(
        Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Magenta, Color.Cyan
    )
    val trueColors = remember { mutableStateOf(selectRandomColors(allColors)) }
    val gameRowStates = remember { mutableStateListOf(GameRowState()) }
    val isGameFinished = remember { mutableStateOf(false) }

    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Your score: ${gameRowStates.size}",
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
            Button(
                onClick = {
                    gameRowStates.clear()
                    gameRowStates.add(GameRowState())
                    isGameFinished.value = false
                    trueColors.value = selectRandomColors(allColors)
                },
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                Text(text = "Play again")
            }
        }
    }
}

@Composable
fun CircularButton(color: Color, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    Button(
        onClick = onClick,
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.background),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = MaterialTheme.colorScheme.onBackground
        )
    ) {}
}

@Composable
fun SelectableColorsRow(colors: List<Color>, clickAction: ((Int) -> Unit)?) {
    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
        colors.forEachIndexed { index, color ->
            CircularButton(
                onClick = {
                    if (clickAction != null) {
                        clickAction(index)
                    }
                },
                color = color,
                modifier = Modifier.size(50.dp)
            )
        }
    }
}

@Composable
fun SmallCircle(color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(color = color)
            .border(2.dp, MaterialTheme.colorScheme.outline, CircleShape)
    )
}

@Composable
fun FeedbackCircles(colors: List<Color>) {
    val spacedBy = 2.dp
    Column(verticalArrangement = Arrangement.spacedBy(spacedBy)) {
        colors.chunked(2).forEach { rowColors ->
            Row(horizontalArrangement = Arrangement.spacedBy(spacedBy)) {
                rowColors.forEach { color ->
                    SmallCircle(color = color, modifier = Modifier.size(24.dp))
                }
            }
        }
    }
}

@Composable
fun GameRow(
    selectedColors: List<Color>,
    feedbackColors: List<Color>,
    clickable: Boolean,
    onSelectColorClick: (Int) -> Unit,
    onCheckClick: () -> Unit
) {
    Row {
        SelectableColorsRow(
            colors = selectedColors,
            clickAction = if (clickable) onSelectColorClick else { _ -> }
        )
        Spacer(modifier = Modifier.padding(horizontal = 2.5.dp))
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
        Spacer(modifier = Modifier.padding(horizontal = 2.5.dp))
        FeedbackCircles(colors = feedbackColors)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab_3Theme {
        GameScreen()
    }
}