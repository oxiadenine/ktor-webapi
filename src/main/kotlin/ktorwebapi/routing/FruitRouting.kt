package ktorwebapi.routing

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.*
import ktorwebapi.controllers.FruitController
import ktorwebapi.models.Fruit

private const val BASE_ROUTE: String = "api/fruits"

fun Route.getFruits(controller: FruitController) {
    get(BASE_ROUTE) {
        controller.getAll()
                .fold({
                    call.respond(it)
                }, { error ->
                    call.respondText(error.message!!)
                })
    }
}

fun Route.getFruit(controller: FruitController) {
    get("$BASE_ROUTE/{id}") {
        val id = call.parameters["id"] ?: let {
            call.response.status(HttpStatusCode.BadRequest)
            call.respondText("")
        }

        controller.getById((id as String).toInt())
                .fold({
                    call.respond(it)
                }, { error ->
                    call.respond(error.message!!)
                })
    }
}

fun Route.createFruit(controller: FruitController) {
    post(BASE_ROUTE) {
        val fruit = call.receive<Fruit>()

        controller.add(fruit)
                .fold({
                    call.respond(it)
                }, { error ->
                    call.respondText(error.message!!)
                })
    }
}

fun Route.editFruit(controller: FruitController) {
    put("$BASE_ROUTE/{id}") {
        val id = call.parameters["id"] ?: let {
            call.response.status(HttpStatusCode.BadRequest)
            call.respondText("")
        }

        val fruit = call.receive<Fruit>()

        if ((id as String).toInt() != fruit.id) {
            call.response.status(HttpStatusCode.BadRequest)
            call.respondText("")
        }

        controller.update(id.toInt(), fruit)
                .fold({
                    call.response.status(HttpStatusCode.NoContent)
                    call.respondText("")
                }, { error ->
                    call.respondText(error.message!!)
                })
    }
}

fun Route.deleteFruit(controller: FruitController) {
    delete("$BASE_ROUTE/{id}") {
        val id = call.parameters["id"] ?: let {
            call.response.status(HttpStatusCode.BadRequest)
            call.respondText("")
        }

        controller.delete((id as String).toInt())
                .fold({
                    call.response.status(HttpStatusCode.NoContent)
                    call.respondText("")
                }, { error ->
                    call.respondText(error.message!!)
                })
    }
}

