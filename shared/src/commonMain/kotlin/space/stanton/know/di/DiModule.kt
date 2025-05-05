package space.stanton.know.di

import io.ktor.client.HttpClient
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import space.stanton.know.networking.getQuestions
import space.stanton.know.networking.ktorClient

val sharedModule = module {
    singleOf(::RealQuestionsPresenter) { bind<QuestionsPresenter>() }
    ktorClient()
}

expect fun platformModule(): Module

fun insertKoin() {
    startKoin {
        modules(platformModule(), sharedModule)
    }
}

interface QuestionsPresenter {
    val message: String
}

class RealQuestionsPresenter(val client: HttpClient) : QuestionsPresenter {
    override val message: String get() = client.getQuestions()
}