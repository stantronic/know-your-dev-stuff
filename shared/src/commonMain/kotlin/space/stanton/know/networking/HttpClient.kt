package space.stanton.know.networking

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf


fun Module.ktorClient() {
    single<HttpClient> { AppHttpClient }
    singleOf(::PeopleService)
}


val AppHttpClient = createHttpClient().config {

}

expect fun createHttpClient(): HttpClient

class PeopleService(private val client: HttpClient) {
    suspend fun getPeople(): String = withContext(Dispatchers.IO) {
        "People ${client.get("https://dummyjson.com/users").bodyAsText()}"
    }
}