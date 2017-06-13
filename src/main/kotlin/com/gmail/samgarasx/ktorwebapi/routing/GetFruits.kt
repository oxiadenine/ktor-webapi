package com.gmail.samgarasx.ktorwebapi.routing

import com.gmail.samgarasx.ktorwebapi.controllers.FruitsController
import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.http.ContentType
import org.jetbrains.ktor.routing.get
import org.jetbrains.ktor.response.contentType
import org.jetbrains.ktor.routing.Route

fun Route.getFruits(controller: FruitsController) {
    get("api/fruits") {
        val jsonResponse = controller.getAll()

        call.response.contentType(ContentType.Application.Json)
        call.respond(jsonResponse.toJsonString(true))
    }
}