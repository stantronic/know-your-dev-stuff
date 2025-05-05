package space.stanton.know.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import space.stanton.know.BuildKonfig


fun Module.questionsRepository() = singleOf(::QuestionsRepository)

class QuestionsRepository(private val client: HttpClient) {
    suspend fun getQuestions(): List<QuestionDto> {
        val response = client.get("https://quizapi.io/api/v1/questions") {
            parameter("apiKey", BuildKonfig.QUIZAPI_KEY)
            parameter("limit", 10)
        }
        return response.body<List<QuestionDto>>()
    }
}
