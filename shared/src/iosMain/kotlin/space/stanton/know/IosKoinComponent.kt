package space.stanton.know

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import space.stanton.know.presentation.QuestionVmo
import space.stanton.know.presentation.QuestionsPresenter
import space.stanton.know.presentation.QuizScore


@Suppress("unused")
object IosQuestionViewModel : KoinComponent {

    private val presenter: QuestionsPresenter by inject<QuestionsPresenter>()

    private val scope = CoroutineScope(Dispatchers.IO)


    fun fetch() {

        scope.launch {
            presenter.currentQuestion.collect {
                onNewQuestion(it)
            }
        }

        scope.launch {
            presenter.score.collect {
                onScore(it)
            }
        }

        presenter.fetch()
    }

    private var onNewQuestion: (QuestionVmo?) -> Unit = {}

    private var onScore: (QuizScore) -> Unit = {}

    fun onNewQuestion(callback: (QuestionVmo?) -> Unit) {
        this.onNewQuestion = callback
    }

    fun onScore(callback: (QuizScore) -> Unit) {
        this.onScore = callback
    }

    fun answer(isCorrect: Boolean) {
        presenter.answerQuestion(isCorrect)
    }
}