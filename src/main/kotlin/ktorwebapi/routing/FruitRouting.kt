package ktorwebapi.routing

import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import ktorwebapi.controllers.FruitController
import ktorwebapi.helpers.JsonResponse
import ktorwebapi.models.Fruit

private const val API_URL: String = "api/fruits"

fun Route.fruits(controller: FruitController) {
    route(API_URL) {
        get {
            val fruits = controller.getAll()

            call.respond(JsonResponse.success(fruits))
        }

        get("{id}") {
            val id = call.parameters["id"]!!.toInt()

            val fruit = controller.getById(id)

            fruit ?: let {
                call.respond(JsonResponse.failure<String>("Fruit does not exists"))
            }

            fruit?.apply {
                call.respond(JsonResponse.success(fruit))
            }
        }

        post {
            val fruit = call.receive<Fruit>()

            val addedFruit = controller.add(fruit)

            call.respond(JsonResponse.success(addedFruit))
        }

        put("{id}") {
            val id = call.parameters["id"]!!.toInt()

            val fruit = call.receive<Fruit>()

            val updatedFruit = controller.update(id, fruit)

            updatedFruit ?: let {
                call.respond(JsonResponse.failure<String>("Fruit does not exists"))
            }

            updatedFruit?.apply {
                call.respond(JsonResponse.success(updatedFruit))
            }
        }

        delete("{id}") {
            val id = call.parameters["id"]!!.toInt()

            val deletedFruit = controller.delete(id)

            deletedFruit ?: let {
                call.respond(JsonResponse.failure<String>("Fruit does not exists"))
            }

            deletedFruit?.apply {
                call.respond(JsonResponse.success(deletedFruit))
            }
        }
    }
}
