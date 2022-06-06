package ktorwebapi.routing

import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

private const val API_URL = "api"

fun Route.api() {
    get(API_URL) {
        call.respond("Welcome to Ktor Web API!")
    }
}
