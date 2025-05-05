package space.stanton.know.networking

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.Module


fun Module.ktorClient() {
    single<HttpClient> { AppHttpClient }
}


val AppHttpClient = createHttpClient().config {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }
}


expect fun createHttpClient(): HttpClient

