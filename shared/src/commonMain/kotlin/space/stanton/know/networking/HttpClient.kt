package space.stanton.know.networking

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.runBlocking
import org.koin.core.module.Module
import space.stanton.know.BuildKonfig


fun Module.ktorClient() {
    single<HttpClient> { AppHttpClient }
}


val AppHttpClient = createHttpClient().config {

}


fun HttpClient.getQuestions(): String =
    runBlocking {
        val response = get("https://quizapi.io/api/v1/questions") {
            parameter("apiKey", BuildKonfig.QUIZAPI_KEY)
            parameter("limit", 10)
        }
        response.bodyAsText()
    }


expect fun createHttpClient(): HttpClient

