package space.stanton.know.ui

import androidx.compose.ui.window.ComposeUIViewController
import space.stanton.know.presentation.QuizScore

@Suppress("FunctionName", "unused")
fun ComposeController(score: QuizScore, toStart: ()->Unit) = ComposeUIViewController {
    ScoreScreen(
        score = score, toStart
    )
}