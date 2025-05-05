package space.stanton.know.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import space.stanton.know.data.QuestionsRepository

fun Module.questionsPresenter(): KoinDefinition<QuestionsPresenter> =
    this.singleOf(::RealQuestionsPresenter) { bind<QuestionsPresenter>() }


interface QuestionsPresenter {

    val currentQuestion: StateFlow<QuestionVmo?>
    val score: StateFlow<QuizScore>
    fun fetch()
    fun answerQuestion(isCorrect: Boolean)
}

@Serializable
data class QuizScore(
    val score: Int,
    val total: Int,
    val complete: Boolean
) {

    fun toJson() = Json.encodeToString(this)


    companion object {
        fun fromJson(json: String) = Json.decodeFromString<QuizScore>(json)
    }
}

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
}


@Suppress("UnusedReceiverParameter")
fun Any.toUnit() = Unit