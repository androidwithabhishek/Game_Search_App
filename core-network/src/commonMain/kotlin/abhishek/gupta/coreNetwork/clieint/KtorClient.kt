package abhishek.gupta.coreNetwork.clieint

import androidx.compose.runtime.traceEventEnd
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.DefaultJson
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


object KtorClient {

    fun getInstance():HttpClient = HttpClient {
       install(ContentNegotiation){
           json(json = Json { ignoreUnknownKeys  = true})

       }

        install(DefaultRequest){
            url{
                host = "api.rawg.io"
                protocol = URLProtocol.HTTPS
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }

        }

        install(HttpTimeout){
            connectTimeoutMillis = 5_000   // Time to connect to server
            socketTimeoutMillis = 10_000   // Idle timeout while waiting for data
            requestTimeoutMillis = 10_000  // Entire request round-trip
        }

        install(HttpCache)
    }

}




