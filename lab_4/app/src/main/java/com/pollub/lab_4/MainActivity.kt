package com.pollub.lab_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pollub.lab_4.screens.game.GameScreen
import com.pollub.lab_4.screens.profile.ProfileScreen
import com.pollub.lab_4.screens.results.ResultsScreen
import com.pollub.lab_4.ui.theme.Lab_4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab_4Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        NavigationGraph(navController)
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "profileScreen") {
        composable(route = "profileScreen") {
            ProfileScreen(
                navigateToGameScreen = { colorCount ->
                    navController.navigate("gameScreen/$colorCount")
                }
            )
        }
        composable(
            route = "gameScreen/{colorCount}",
            arguments = listOf(navArgument("colorCount") { type = NavType.IntType })
        ) { backStackEntry ->
            val colorCount = backStackEntry.arguments?.getInt("colorCount")
            GameScreen(
                colorCount = colorCount ?: throw IllegalStateException("Color count not provided"),
                onBackButtonClicked = {
                    navController.popBackStack()
                    // TODO: add form clearing
                },
                onGoToResultsScreenButtonClicked = { attemptCount: Int ->
                    navController.navigate("resultsScreen/$attemptCount")
                }
            )
        }
        composable(
            route = "resultsScreen/{attemptCount}",
            arguments = listOf(navArgument("attemptCount") { type = NavType.IntType })
        ) { backStackEntry ->
            val attemptCount = backStackEntry.arguments?.getInt("attemptCount")!!
            ResultsScreen(
                attemptCount = attemptCount,
                onLogoutButtonClicked = {
                    navController.popBackStack()
                    navController.popBackStack()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Lab_4Theme {
        MainScreen()
    }
}