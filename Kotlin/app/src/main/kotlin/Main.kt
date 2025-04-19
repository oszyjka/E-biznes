import io.github.cdimascio.dotenv.dotenv
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.delay

@Serializable
data class WebhookMessage(val content: String)

fun main() = runBlocking {
    val dotenv = dotenv()
    val webhookURL = dotenv["WEBHOOK_URL"] 
    println("Now you can enter your message to send to server.Typing 'exit' will quit the app.")
    println("Enter message:")

    while (true) {
        val inputMessage = readLine() 

        if (inputMessage.isNullOrBlank()) {
            delay(100) 
            continue
        }

        if (inputMessage.equals("exit", ignoreCase = true)) break

        sendMessageToServer(webhookURL, inputMessage)
    }
}

suspend fun sendMessageToServer(webhookURL: String,inputMessage: String) {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    val response: HttpResponse = client.post(webhookURL) {
        contentType(ContentType.Application.Json)
        setBody(WebhookMessage(content = inputMessage))
    }

    client.close()
}
