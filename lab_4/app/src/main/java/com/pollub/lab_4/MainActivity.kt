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
import com.pollub.lab_4.ui.theme.Lab_4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab_4Theme {
                // A surface container using the 'background' color from the theme
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
        composable("profileScreen") { backStackEntry ->
            // tutaj odczytujemy wartość parametru przekazanego ze Screen1
//            val colorCount = backStackEntry.arguments?.getInt("colorCount")!!
            // dodajemy Screen2 i przekazujemy do niego wartość parametru
            ProfileScreen(
                onNextButtonClicked = { colorCount -> navController.navigate("gameScreen/$colorCount") }
            )
        }

        composable(
            // parametr podaje się w ścieżce/trasie
            "gameScreen/{colorCount}",
            // na liście określamy typy poszczególnych argumentów
            arguments = listOf(navArgument("colorCount") { type = NavType.IntType })
        ) { backStackEntry ->
            // tutaj odczytujemy wartość parametru przekazanego ze Screen1
            val colorCount = backStackEntry.arguments?.getInt("colorCount")!!
            // dodajemy Screen2 i przekazujemy do niego wartość parametru
            GameScreen(colorCount,
                // obsługa zdarzeń związanych z nawigacją została „wyciągnięta”
                // tutaj (ze względu na dostęp do kontrolera nawigacji)
                // powrót ze Screen2 do Screen1
                onGoBackButtonClicked =
                { replyForScreen1 ->
                    // ustawienie „wyniku” wykonania Screen2 (wynik pobierze Screen1)
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        "reply",
                        replyForScreen1
                    )
                    // powrót tam skąd przyszliśmy
                    navController.popBackStack()
                }
            )
            // przejście ze Screen2 do Screen3
            {
                //nawigacja do Screen3 i przekazanie parametrów (Screen3 ma
                //dwa obowiązkowe i jeden opcjonalny argument)

                //przekazanie konkretnej wartości parametru opcjonalnego
                //navController.navigate("screen3/1str/2?optionalArgument=3")
                //użycie wartości domyślnej parametru opcjonalnego
                navController.navigate("screen3/1str/2")
            }
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