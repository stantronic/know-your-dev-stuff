package space.stanton.know.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import space.stanton.know.android.theme.MyApplicationTheme
import space.stanton.know.android.welcome.WelcomeScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import space.stanton.know.android.questions.QuestionsScreen
import space.stanton.know.di.insertKoin

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
                        QuestionsScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
