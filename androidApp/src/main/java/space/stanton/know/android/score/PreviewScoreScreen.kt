package space.stanton.know.android.score

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import space.stanton.know.presentation.QuizScore
import space.stanton.know.ui.ScoreScreen


@Preview
@Composable
fun PreviewScoreScreen() {
    ScoreScreen(
        QuizScore(5, 10, true)
    )
}