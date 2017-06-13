package com.gmail.samgarasx.ktorwebapi.routing

import com.gmail.samgarasx.ktorwebapi.controllers.FruitsController
import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.http.HttpStatusCode
import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.routing.Route
import org.jetbrains.ktor.routing.delete

fun Route.deleteFruit(controller: FruitsController) {
    delete("api/fruits/{id}") {
        val id = call.parameters["id"] ?: let {
            call.response.status(HttpStatusCode.BadRequest)
            call.respondText("")
        }

        if (controller.getById((id as String).toInt()).isEmpty()) {
            call.response.status(HttpStatusCode.NotFound)
            call.respondText("")
        }

        controller.delete(id.toInt())

        call.response.status(HttpStatusCode.NoContent)
        call.respondText("")
    }
}
