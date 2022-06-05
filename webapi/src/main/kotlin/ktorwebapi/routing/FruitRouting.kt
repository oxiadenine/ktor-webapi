package ktorwebapi.routing

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ktorwebapi.controllers.FruitController
import ktorwebapi.helpers.JsonResponse
import ktorwebapi.models.Fruit

private const val API_URL = "api/fruits"

fun Route.fruits(controller: FruitController) {
    route(API_URL) {
        get {
            val fruits = controller.all()

            val response = JsonResponse.success(fruits)

            call.respond(response)
        }

        get("{id}") {
            val id = call.parameters["id"]!!.toInt()

            val fruit = controller.one(id)

            val response = if (fruit == null) {
                JsonResponse.failure("Fruit does not exists")
            } else {
                JsonResponse.success(fruit)
            }

            call.respond(response)
        }

        post {
            val newFruit = call.receive<Fruit>()

            val fruit = controller.new(newFruit)

            val response = if (fruit == null) {
                JsonResponse.failure("Fruit already exists")
            } else {
                JsonResponse.success(fruit)
            }

            call.respond(response)
        }

        put("{id}") {
            val id = call.parameters["id"]!!.toInt()

            val editFruit = call.receive<Fruit>()

            val fruit = controller.edit(id, editFruit)

            val response = if (fruit == null) {
                JsonResponse.failure("Fruit does not exists")
            } else {
                JsonResponse.success(fruit)
            }

            call.respond(response)
        }

        delete("{id}") {
            val id = call.parameters["id"]!!.toInt()

            val fruit = controller.delete(id)

            val response = if (fruit == null) {
                JsonResponse.failure("Fruit does not exists")
            } else {
                JsonResponse.success(fruit)
            }

            call.respond(response)
        }
    }
}
