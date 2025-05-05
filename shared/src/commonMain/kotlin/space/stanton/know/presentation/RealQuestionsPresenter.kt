package space.stanton.know.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import space.stanton.know.data.QuestionsRepository

class RealQuestionsPresenter(private val repository: QuestionsRepository) : QuestionsPresenter {

    private val questionFlow = MutableStateFlow<List<QuestionVmo>>(emptyList())

    override val currentQuestion = MutableStateFlow<QuestionVmo?>(null)
    override val score = MutableStateFlow(QuizScore(0, 0, false))

    private val scope = CoroutineScope(Dispatchers.Default)

    private var scoreNumber = 0
    private var index = 0

    override fun fetch() = scope.launch {
        repository.getTags()

        val questions = repository.getQuestions().map { it.toVmo() }
        questionFlow.update { questions }
        currentQuestion.update { questionFlow.value.firstOrNull() }
    }.toUnit()

    override fun answerQuestion(isCorrect: Boolean) {
        if (isCorrect) scoreNumber++
        index++
        currentQuestion.update { questionFlow.value.getOrNull(index) }

        score.update {
            QuizScore(
                total = questionFlow.value.size,
                score = scoreNumber,
                complete = index == questionFlow.value.size
            )
        }
    }

    override fun reset() {
        scoreNumber = 0
        index = 0
        score.update { QuizScore(0, 0, false) }
        questionFlow.update { emptyList() }
        currentQuestion.update { null }
    }
}