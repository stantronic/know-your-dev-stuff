package space.stanton.know.android.questions

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import space.stanton.know.android.AppScreen
import space.stanton.know.android.ui.LargeSpacer
import space.stanton.know.android.ui.MediumSpacer
import space.stanton.know.di.QuestionsViewModel

@Composable
fun QuestionsScreen(viewModel: QuestionsViewModel = koinViewModel()) {
    AppScreen {
        LaunchedEffect(Unit) {
            viewModel.fetch()
        }

        val questions by viewModel.questionFlow.collectAsState()

        Column {
            val context = LocalContext.current
            questions.firstOrNull()?.let{
                Text(it.text)
                LargeSpacer()
                it.answers.forEach { answer ->

                    Card(onClick = {
                        if (answer.isCorrect) Toast.makeText(context, "Correct", Toast.LENGTH_SHORT)
                            .show()
                        else Toast.makeText(context, "Wrong!", Toast.LENGTH_SHORT).show()
                    }) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Text(answer.text)
                        }
                    }
                    MediumSpacer()
                }
                MediumSpacer()
            }
        }
    }
}

