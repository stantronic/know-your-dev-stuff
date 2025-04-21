package space.stanton.know.networking

import io.ktor.client.HttpClient

actual fun createHttpClient(): HttpClient {
    return HttpClient()
}