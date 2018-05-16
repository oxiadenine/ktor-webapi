package ktorwebapi

import com.beust.klaxon.JsonObject
import ktorwebapi.data.datasources.FruitDataSource
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.logging.CallLogging
import org.jetbrains.ktor.routing.*
import com.github.salomonbrys.kodein.conf.ConfigurableKodein
import com.github.salomonbrys.kodein.instance
import ktorwebapi.di.appModule
import ktorwebapi.routing.*
import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.application.ApplicationCallPipeline
import org.jetbrains.ktor.content.TextContent
import org.jetbrains.ktor.http.ContentType
import org.jetbrains.ktor.request.acceptItems
import org.jetbrains.ktor.transform.transform

fun Application.main() {
    val kodein = ConfigurableKodein()

    kodein.addImport(appModule)

    kodein.instance<FruitDataSource>().initDatabase()

    install(DefaultHeaders)
    install(CallLogging)
    install(Routing) {
        getFruits(kodein.instance())
        getFruit(kodein.instance())
        createFruit(kodein.instance())
        editFruit(kodein.instance())
        deleteFruit(kodein.instance())
    }

    intercept(ApplicationCallPipeline.Infrastructure) { call ->
        val headers = call.request.acceptItems()
        if (headers.any { it.value == ContentType.Application.Json.toString() }) {
            call.transform.register<JsonObject> { value ->
                TextContent(value.toJsonString(true), ContentType.Application.Json)
            }
        }
    }
}
