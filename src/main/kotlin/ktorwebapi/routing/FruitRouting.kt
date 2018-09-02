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
            val fruits = controller.all()

            val response = JsonResponse.success(fruits)

            call.respond(response)
        }

        get("{id}") {
            val id = call.parameters["id"]!!.toLong()

            val fruit = controller.one(id)

            val response = if (fruit == null) {
                JsonResponse.failure<String>("Fruit does not exists")
            } else {
                JsonResponse.success(fruit)
            }

            call.respond(response)
        }

        post {
            val newFruit = call.receive<Fruit>()

            val fruit = controller.new(newFruit)

            println(fruit)

            val response = if (fruit == null) {
                JsonResponse.failure("Fruit already exists")
            } else {
                JsonResponse.success(fruit)
            }

            call.respond(response)
        }

        put("{id}") {
            val id = call.parameters["id"]!!.toLong()

            val editFruit = call.receive<Fruit>()

            val fruit = controller.edit(id, editFruit)

            val response = if (fruit == null) {
                JsonResponse.failure<String>("Fruit does not exists")
            } else {
                JsonResponse.success(fruit)
            }

            call.respond(response)
        }

        delete("{id}") {
            val id = call.parameters["id"]!!.toLong()

            val fruit = controller.delete(id)

            val response = if (fruit == null) {
                JsonResponse.failure<String>("Fruit does not exists")
            } else {
                JsonResponse.success(fruit)
            }

            call.respond(response)
        }
    }
}
