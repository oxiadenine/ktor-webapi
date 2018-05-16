package ktorwebapi.routing

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import ktorwebapi.controllers.FruitsController
import ktorwebapi.models.Fruit
import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.application.receive
import org.jetbrains.ktor.http.HttpStatusCode
import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.routing.*

private val BASE_ROUTE: String = "api/fruits"

fun Route.getFruits(controller: FruitsController) {
    get(BASE_ROUTE) {
        val jsonResponse = controller.getAll()
        call.respond(jsonResponse)
    }
}

fun Route.getFruit(controller: FruitsController) {
    get("$BASE_ROUTE/{id}") {
        val id = call.parameters["id"] ?: let {
            call.response.status(HttpStatusCode.BadRequest)
            call.respondText("")
        }

        val jsonResponse = controller.getById((id as String).toInt())
        if (jsonResponse.isEmpty()) {
            call.response.status(HttpStatusCode.NotFound)
            call.respondText("")
        } else
            call.respond(jsonResponse)
    }
}

fun Route.createFruit(controller: FruitsController) {
    post(BASE_ROUTE) {
        val jsonRequest = call.request.receive<String>()
        if (jsonRequest.isEmpty()) {
            call.response.status(HttpStatusCode.BadRequest)
            call.respondText("")
        } else {
            val stringBuilder = StringBuilder(jsonRequest)
            val json = Parser().parse(stringBuilder) as JsonObject

            val fruit = Fruit(
                    0,
                    json["no"] as String,
                    json["description"] as String)

            val jsonResponse = controller.add(fruit)
            call.respond(jsonResponse)
        }
    }
}

fun Route.editFruit(controller: FruitsController) {
    put("$BASE_ROUTE/{id}") {
        val id = call.parameters["id"] ?: let {
            call.response.status(HttpStatusCode.BadRequest)
            call.respondText("")
        }

        val jsonRequest = call.request.receive<String>()
        if (jsonRequest.isEmpty()) {
            call.response.status(HttpStatusCode.BadRequest)
            call.respondText("")
        } else {
            val stringBuilder = StringBuilder(jsonRequest)
            val json = Parser().parse(stringBuilder) as JsonObject

            val fruit = Fruit(
                    json["id"] as Int,
                    json["no"] as String,
                    json["description"] as String)

            if ((id as String).toInt() != fruit.id) {
                call.response.status(HttpStatusCode.BadRequest)
                call.respondText("")
            }

            if (controller.getById(id.toInt()).isEmpty()) {
                call.response.status(HttpStatusCode.NotFound)
                call.respondText("")
            }

            val jsonResponse = controller.update(id.toInt(), fruit)
            if (jsonResponse.isEmpty()) {
                call.response.status(HttpStatusCode.NoContent)
                call.respondText("")
            }
        }
    }
}

fun Route.deleteFruit(controller: FruitsController) {
    delete("$BASE_ROUTE/{id}") {
        val id = call.parameters["id"] ?: let {
            call.response.status(HttpStatusCode.BadRequest)
            call.respondText("")
        }

        if (controller.getById((id as String).toInt()).isEmpty()) {
            call.response.status(HttpStatusCode.NotFound)
            call.respondText("")
        }

        val jsonResponse = controller.delete(id.toInt())
        if (jsonResponse.isEmpty()) {
            call.response.status(HttpStatusCode.NoContent)
            call.respondText("")
        } else
            call.respond(jsonResponse)
    }
}

