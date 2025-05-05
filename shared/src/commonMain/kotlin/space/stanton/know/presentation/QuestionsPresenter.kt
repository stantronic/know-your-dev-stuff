package space.stanton.know.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import space.stanton.know.data.QuestionsRepository

fun Module.questionsPresenter(): KoinDefinition<QuestionsPresenter> =
    this.singleOf(::RealQuestionsPresenter) { bind<QuestionsPresenter>() }


interface QuestionsPresenter {

    val currentQuestion: StateFlow<QuestionVmo?>
    fun fetch()
    fun answerQuestion(isCorrect: Boolean)
}

class RealQuestionsPresenter(private val repository: QuestionsRepository) : QuestionsPresenter {
    private val questionFlow = MutableStateFlow<List<QuestionVmo>>(emptyList())

    override val currentQuestion = MutableStateFlow<QuestionVmo?>(null)

    private val scope = CoroutineScope(Dispatchers.Default)

    private var score = 0
    private var index = 0

    override fun fetch() = scope.launch {
        val questions = repository.getQuestions().map { it.toVmo() }
        questionFlow.update { questions }
        currentQuestion.update { questionFlow.value.firstOrNull() }
    }.toUnit()

    override fun answerQuestion(isCorrect: Boolean) {
        if (isCorrect) score++
        index++
        currentQuestion.update { questionFlow.value.getOrNull(index) }
    }
}


@Suppress("UnusedReceiverParameter")
fun Any.toUnit() = Unit