package com.pollub.masterand

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import com.pollub.masterand.screens.game.GameScreen
import com.pollub.masterand.screens.profile.ProfileScreen
import com.pollub.masterand.screens.results.ResultsScreen
import com.pollub.masterand.ui.theme.MasterAndTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MasterAndTheme {
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
    val transitionDuration = 1000
    val enterTransitionEasing = EaseIn
    val exitTransitionEasing = EaseOut

    NavHost(navController = navController, startDestination = "profileScreen") {
        composable(
            route = "profileScreen",
            enterTransition = {
                fadeIn() + slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(transitionDuration, easing = enterTransitionEasing)
                )
            },
            exitTransition = {
                fadeOut() + slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(transitionDuration, easing = exitTransitionEasing)
                )
            }
        ) {
            ProfileScreen(
                navigateToGameScreen = { colorCount ->
                    navController.navigate("gameScreen/$colorCount")
                }
            )
        }
        composable(
            route = "gameScreen/{colorCount}",
            arguments = listOf(navArgument("colorCount") { type = NavType.IntType }),
            enterTransition = {
                fadeIn() + slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(transitionDuration, easing = enterTransitionEasing)
                )
            },
            exitTransition = {
                fadeOut() + slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(transitionDuration, easing = exitTransitionEasing)
                )
            }
        ) { backStackEntry ->
            val colorCount = backStackEntry.arguments?.getInt("colorCount")
            GameScreen(
                colorCount = colorCount ?: throw IllegalStateException("Color count missing"),
                navigateToProfileScreen = { navController.popBackStack() },
                navigateToResultsScreen = { attemptCount: Int ->
                    navController.navigate("resultsScreen/$attemptCount")
                }
            )
        }
        composable(
            route = "resultsScreen/{attemptCount}",
            arguments = listOf(navArgument("attemptCount") { type = NavType.IntType }),
            enterTransition = {
                fadeIn() + slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(transitionDuration, easing = enterTransitionEasing)
                )
            },
            exitTransition = {
                fadeOut() + slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(transitionDuration, easing = exitTransitionEasing)
                )
            }
        ) { backStackEntry ->
            val attemptCount = backStackEntry.arguments?.getInt("attemptCount")
            ResultsScreen(
                attemptCount = attemptCount ?: throw IllegalStateException("Attempt count missing"),
                navigateToGameScreen = { navController.popBackStack() },
                navigateToProfileScreen = {
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
    MasterAndTheme {
        MainScreen()
    }
}