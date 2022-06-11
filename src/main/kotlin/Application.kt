import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import routing.fruitRoute

fun Application.main() {
    Database.connect(environment.config.config("database"))

    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        allowHeader("user-agent")

        allowCredentials = true
    }

    install(ContentNegotiation) {
        json(Json {
            encodeDefaults = false
            prettyPrint = true
        })
    }

    install(StatusPages) {
        exception<Throwable> { call, _ ->
            call.respond(HttpStatusCode.InternalServerError)
        }
    }

    install(Routing) {
        route("/api") {
            get {
                call.respondText("Welcome to Ktor Web API!")
            }

            fruitRoute()
        }
    }
}