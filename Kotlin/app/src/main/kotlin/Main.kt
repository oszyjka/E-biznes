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

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import javax.security.auth.login.LoginException
import net.dv8tion.jda.api.requests.GatewayIntent

@Serializable
data class WebhookMessage(val content: String)

fun main() = runBlocking {
    val dotenv = dotenv()
    val mode = dotenv["APP_MODE"] ?: "bot"

    if (mode == "webhook") {
        runBlocking {
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
    } else if (mode == "bot") {
        val token = dotenv["BOT_TOKEN"]
        runServerBot(token)
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

fun runServerBot(token: String) {
    try {
        val jda = JDABuilder.createDefault(token)
            .enableIntents(
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.MESSAGE_CONTENT
            )
            .addEventListeners(BotListener())
            .build()
    } catch (e: LoginException) {
        println("Failed to login: ${e.message}")
    }
}

class BotListener : ListenerAdapter() {
    private val categories = listOf("Food", "Sport equipment", "Clothes", "Furniture")
    override fun onMessageReceived(event: MessageReceivedEvent) {
        val author = event.author
        val message = event.message

        if (author.isBot) return
        val displayMessage = message.contentDisplay
        println("Received message from ${author.name}: $displayMessage")

        val content = message.contentRaw.trim()
        if (content.equals("!categories", ignoreCase = true)) {
            val response = "Available categories:\n" + categories.joinToString("\n") { "- $it" }
            message.reply(response).queue()
        }
    }
}