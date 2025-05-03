package space.stanton.know.android.questions

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import org.koin.androidx.compose.koinViewModel
import space.stanton.know.android.AppScreen
import space.stanton.know.di.QuestionsViewModel

@Composable
fun QuestionsScreen(viewModel: QuestionsViewModel = koinViewModel()) {
    AppScreen {
        LaunchedEffect(Unit) {

        }

        Text(viewModel.getMessage())
    }
}

