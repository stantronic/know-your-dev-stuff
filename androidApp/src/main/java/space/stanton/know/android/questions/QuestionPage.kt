package space.stanton.know.android.questions

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import space.stanton.know.android.ui.LargeSpacer
import space.stanton.know.android.ui.MediumSpacer
import space.stanton.know.presentation.QuestionVmo

@Composable
fun QuestionPage(question: QuestionVmo, onQuestionAnswered: (Boolean) -> Unit) {
    val context = LocalContext.current
    Column {
        Text(question.text)
        LargeSpacer()
        question.answers.forEach { answer ->

            Card(onClick = {
                if (answer.isCorrect) Toast.makeText(context, "Correct", Toast.LENGTH_SHORT)
                    .show()
                else Toast.makeText(context, "Wrong!", Toast.LENGTH_SHORT).show()
                onQuestionAnswered(answer.isCorrect)
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