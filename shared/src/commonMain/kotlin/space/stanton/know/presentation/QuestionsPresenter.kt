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

    fun fetch()

    val questionFlow: StateFlow<List<QuestionVmo>>
}

class RealQuestionsPresenter(private val repository: QuestionsRepository) : QuestionsPresenter {
    override val questionFlow = MutableStateFlow<List<QuestionVmo>>(emptyList())

    private val scope = CoroutineScope(Dispatchers.Default)

    override fun fetch() = scope.launch {
        val questions = repository.getQuestions().map { it.toVmo() }
        questionFlow.update { questions }
    }.toUnit()
}


@Suppress("UnusedReceiverParameter")
fun Any.toUnit() = Unit