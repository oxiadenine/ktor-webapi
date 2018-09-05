package ktorwebapi.routing

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get

private const val API_URL = "api"

fun Route.api() {
    get(API_URL) {
        call.respond("Welcome to Ktor Web API")
    }
}
