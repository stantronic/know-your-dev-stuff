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
import androidx.lifecycle.compose.LocalLifecycleOwner
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
        val question by viewModel.currentQuestion.collectAsState()
        val score by viewModel.score.collectAsState()
        val lifecycleOwner = LocalLifecycleOwner.current
        LaunchedEffect(lifecycleOwner) {
            if (question == null) {
                viewModel.fetch()
            }
        }

        LaunchedEffect(score.complete) {
            if (score.complete) {
                val json = score.toJson()
                viewModel.reset()
                onComplete(json)
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