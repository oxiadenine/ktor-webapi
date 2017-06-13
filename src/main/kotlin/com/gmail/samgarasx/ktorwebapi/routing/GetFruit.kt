package com.gmail.samgarasx.ktorwebapi.routing

import com.gmail.samgarasx.ktorwebapi.controllers.FruitsController
import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.http.ContentType
import org.jetbrains.ktor.http.HttpStatusCode
import org.jetbrains.ktor.routing.get
import org.jetbrains.ktor.response.contentType
import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.routing.Route

fun Route.getFruit(controller: FruitsController) {
    get("api/fruits/{id}") {
        val id = call.parameters["id"] ?: let {
            call.response.status(HttpStatusCode.BadRequest)
            call.respondText("")
        }

        val jsonResponse = controller.getById((id as String).toInt())
        if (jsonResponse.isEmpty()) {
            call.response.status(HttpStatusCode.NotFound)
            call.respondText("")
        } else {
            call.response.contentType(ContentType.Application.Json)
            call.respond(jsonResponse.toJsonString(true))
        }
    }
}
