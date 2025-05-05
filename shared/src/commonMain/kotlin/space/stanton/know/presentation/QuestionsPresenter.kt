package space.stanton.know.presentation

import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf

fun Module.questionsPresenter(): KoinDefinition<QuestionsPresenter> =
    this.singleOf(::RealQuestionsPresenter) { bind<QuestionsPresenter>() }


interface QuestionsPresenter {

    val currentQuestion: StateFlow<QuestionVmo?>
    val score: StateFlow<QuizScore>
    fun fetch()
    fun answerQuestion(isCorrect: Boolean)
    fun reset()
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


@Suppress("UnusedReceiverParameter")
fun Any.toUnit() = Unit