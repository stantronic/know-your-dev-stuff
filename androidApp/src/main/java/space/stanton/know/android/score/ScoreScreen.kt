package space.stanton.know.android.score

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import space.stanton.know.android.AppScreen
import space.stanton.know.android.ui.AppButton
import space.stanton.know.presentation.QuizScore

@Composable
fun ScoreScreen(score: QuizScore, toStart: () -> Unit) {
    AppScreen {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Your score is ${score.score} out of ${score.total}")
            AppButton("Back to start", onClick = toStart)
        }
    }
}
