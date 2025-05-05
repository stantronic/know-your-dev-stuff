package space.stanton.know.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import space.stanton.know.BuildKonfig


fun Module.questionsRepository() = singleOf(::QuestionsRepository)

class QuestionsRepository(private val client: HttpClient) {
    suspend fun getQuestions(): List<QuestionDto> {
        return listOf("git", "bash", "docker", "java", "Swift")
            .map { getQuestionsByTag(it) }
            .flatten()
            .shuffled()
            .take(10)
    }


    private suspend fun getQuestionsByTag(tag: String): List<QuestionDto> = try {
        client.get("https://quizapi.io/api/v1/questions?tags=bash&tags=git") {
            parameter("tags", tag)
            parameter("apiKey", BuildKonfig.QUIZAPI_KEY)
            parameter("limit", 20)
        }.body<List<QuestionDto>>()
    } catch (e: Exception) {
        emptyList()
    }


    suspend fun getTags() {
        val response = client.get("https://quizapi.io/api/v1/tags") {
            parameter("apiKey", BuildKonfig.QUIZAPI_KEY)
        }
        println(response.bodyAsText())
    }
}
