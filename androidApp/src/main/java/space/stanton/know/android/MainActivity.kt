package space.stanton.know.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import space.stanton.know.android.questions.QuestionsScreen
import space.stanton.know.android.theme.MyApplicationTheme
import space.stanton.know.android.welcome.WelcomeScreen
import space.stanton.know.di.insertKoin
import space.stanton.know.presentation.QuizScore
import space.stanton.know.ui.ScoreScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        insertKoin()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(
                    navController,
                    startDestination = "welcome"
                ) {
                    composable("welcome") {
                        WelcomeScreen(onBegin = {
                            navController.navigate("questions")
                        })
                    }
                    composable("questions") {
                        QuestionsScreen(
                            onComplete = { value ->
                                println(value)
                                navController.navigate("score?value=$value")
                            }
                        )
                    }
                    composable(
                        "score?value={value}",
                        arguments = listOf(
                            navArgument("value") {
                                defaultValue = ""
                                type = NavType.StringType
                            })
                    ) {
                        AppScreen {
                            ScoreScreen(
                                score = QuizScore.fromJson(
                                    it.arguments?.getString("value").orEmpty()
                                ),
                                toStart = {
                                    navController.navigate("welcome")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}