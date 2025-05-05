package space.stanton.know.android.questions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import space.stanton.know.android.AppScreen
import space.stanton.know.android.ui.LargeSpacer
import space.stanton.know.di.QuestionsViewModel

@Composable
fun QuestionsScreen(
    viewModel: QuestionsViewModel = koinViewModel(),
    onComplete: (String) -> Unit = {}
) {
    AppScreen {
        LaunchedEffect(Unit) { viewModel.fetch() }

        val question by viewModel.currentQuestion.collectAsState()
        val score by viewModel.score.collectAsState()

        LaunchedEffect(score.complete) {
            if (score.complete) {
                onComplete(score.toJson())
            }
        }

        if (question == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Fetching...")
                    LargeSpacer()
                    CircularProgressIndicator()
                }
            }
        }
        question?.let {
            QuestionPage(it, viewModel::answerQuestion)
        }
    }
}