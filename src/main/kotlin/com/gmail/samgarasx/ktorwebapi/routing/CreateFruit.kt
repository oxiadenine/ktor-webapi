package com.gmail.samgarasx.ktorwebapi.routing

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.gmail.samgarasx.ktorwebapi.controllers.FruitsController
import com.gmail.samgarasx.ktorwebapi.models.Fruit
import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.application.receive
import org.jetbrains.ktor.http.ContentType
import org.jetbrains.ktor.http.HttpStatusCode
import org.jetbrains.ktor.routing.post
import org.jetbrains.ktor.response.contentType
import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.routing.Route

fun Route.createFruit(controller: FruitsController) {
    post("api/fruits") {
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

            val jsonResponse = controller.add(fruit)

            call.response.contentType(ContentType.Application.Json)
            call.respond(jsonResponse.toJsonString(true))
        }
    }
}
