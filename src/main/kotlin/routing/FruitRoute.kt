package routing

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import repository.FruitRepository
import kotlinx.serialization.Serializable

@Serializable
data class FruitPost(val name: String, val description: String)

@Serializable
data class FruitGet(val id: Int, val name: String, val description: String)

@Serializable
data class FruitPut(val description: String)

fun Route.fruitRoute() = route("/fruits") {
    intercept(ApplicationCallPipeline.Call) {
        call.parameters["id"]?.let { idParam ->
            try {
                idParam.toInt()
            } catch (ex: NumberFormatException) {
                call.respond(HttpStatusCode.BadRequest, "Fruit Id must be a number.")

                return@intercept
            }
        }
    }

    get {
        val fruits = FruitRepository.read().getOrThrow().map { fruitEntity ->
            FruitGet(fruitEntity.id, fruitEntity.name, fruitEntity.description)
        }.sortedBy { fruit -> fruit.name }

        call.respond(HttpStatusCode.OK, fruits)
    }

    post {
        val fruit = call.receive<FruitPost>()

        FruitRepository.create(fruit.name, fruit.description).getOrThrow()

        call.respond(HttpStatusCode.NoContent)
    }

    get("/{id}") {
        val id = call.parameters["id"]!!.toInt()

        FruitRepository.read(id).getOrThrow()?.let { fruitEntity ->
            val fruit = FruitGet(fruitEntity.id, fruitEntity.name, fruitEntity.description)

            call.respond(HttpStatusCode.OK, fruit)
        } ?: call.respond(HttpStatusCode.NotFound, "Fruit with Id=$id not found.")
    }

    put("/{id}") {
        val id = call.parameters["id"]!!.toInt()

        FruitRepository.read(id).getOrThrow()?.run {
            val fruit = call.receive<FruitPut>()

            FruitRepository.update(id, mapOf(FruitTable.description to fruit.description)).getOrThrow()

            call.respond(HttpStatusCode.NoContent)
        } ?: call.respond(HttpStatusCode.NotFound, "Fruit with Id=$id not found.")
    }

    delete("/{id}") {
        val id = call.parameters["id"]!!.toInt()

        FruitRepository.read(id).getOrThrow()?.run {
            FruitRepository.delete(id).getOrThrow()

            call.respond(HttpStatusCode.NoContent)
        } ?: call.respond(HttpStatusCode.NotFound, "Fruit with Id=$id not found.")
    }
}