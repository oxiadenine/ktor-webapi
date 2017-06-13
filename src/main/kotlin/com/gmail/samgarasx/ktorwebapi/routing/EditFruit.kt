package com.gmail.samgarasx.ktorwebapi.routing

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.gmail.samgarasx.ktorwebapi.controllers.FruitsController
import com.gmail.samgarasx.ktorwebapi.models.Fruit
import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.application.receive
import org.jetbrains.ktor.http.HttpStatusCode
import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.routing.Route
import org.jetbrains.ktor.routing.put

fun Route.editFruit(controller: FruitsController) {
    put("api/fruits/{id}") {
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

            controller.update(id.toInt(), fruit)

            call.response.status(HttpStatusCode.NoContent)
            call.respondText("")
        }
    }
}
