package space.stanton.know.presentation

import kotlinx.coroutines.runBlocking
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import space.stanton.know.data.QuestionsRepository

fun Module.questionsPresenter(): KoinDefinition<QuestionsPresenter> =
    this.singleOf(::RealQuestionsPresenter) { bind<QuestionsPresenter>() }


interface QuestionsPresenter {
    val message: String
}

class RealQuestionsPresenter(private val repository: QuestionsRepository) : QuestionsPresenter {
    override val message: String
        get() = runBlocking {
            repository.getQuestions().map {
                it.question
            }.joinToString("\n\n")
        }
}



