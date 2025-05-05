package space.stanton.know.android.questions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel
import space.stanton.know.android.AppScreen
import space.stanton.know.di.QuestionsViewModel

@Composable
fun QuestionsScreen(viewModel: QuestionsViewModel = koinViewModel()) {
    AppScreen {
        LaunchedEffect(Unit) {
            viewModel.fetch()
        }

        val question by viewModel.currentQuestion.collectAsState()
        question?.let {
            QuestionPage(it, viewModel::answerQuestion)
        }
    }
}