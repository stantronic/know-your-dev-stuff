package space.stanton.know

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import space.stanton.know.presentation.QuestionVmo
import space.stanton.know.presentation.QuestionsPresenter


@Suppress("unused")
object IosQuestionViewModel : KoinComponent {

    private val presenter: QuestionsPresenter by inject<QuestionsPresenter>()

    fun fetch() {
        CoroutineScope(Dispatchers.IO).launch {
            presenter.currentQuestion.collect {
                onNewQuestion(it)
            }
        }

        presenter.fetch()
    }

    private var onNewQuestion: (QuestionVmo?) -> Unit = {}

    fun onNewQuestion(callback: (QuestionVmo?) -> Unit) {
        this.onNewQuestion = callback
    }

    fun answer(isCorrect: Boolean) {
        presenter.answerQuestion(isCorrect)
    }
}