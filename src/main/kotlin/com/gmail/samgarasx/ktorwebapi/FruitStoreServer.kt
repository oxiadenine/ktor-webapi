package com.gmail.samgarasx.ktorwebapi

import com.beust.klaxon.JsonObject
import com.gmail.samgarasx.ktorwebapi.data.datasources.FruitDataSource
import com.gmail.samgarasx.ktorwebapi.routing.*
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.features.Compression
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.host.embeddedServer
import org.jetbrains.ktor.logging.CallLogging
import org.jetbrains.ktor.netty.Netty
import org.jetbrains.ktor.routing.*
import com.github.salomonbrys.kodein.conf.ConfigurableKodein
import com.github.salomonbrys.kodein.instance
import com.gmail.samgarasx.ktorwebapi.di.appModule
import org.jetbrains.ktor.application.ApplicationCallPipeline
import org.jetbrains.ktor.content.TextContent
import org.jetbrains.ktor.http.ContentType
import org.jetbrains.ktor.request.acceptItems
import org.jetbrains.ktor.transform.transform


class FruitStoreServer {
    private val kodein: ConfigurableKodein = ConfigurableKodein()

    init {
        this.kodein.addImport(appModule)
    }

    fun start() {
        embeddedServer(Netty, 8080) {
            kodein.instance<FruitDataSource>().initDatabase()

            install(DefaultHeaders)
            install(Compression)
            install(CallLogging)

            intercept(ApplicationCallPipeline.Infrastructure) { call ->
                val headers = call.request.acceptItems()
                if (headers.any { it.value == ContentType.Application.Json.toString() }) {
                    call.transform.register<JsonObject> { value ->
                        TextContent(value.toJsonString(true), ContentType.Application.Json)
                    }
                }
            }

            install(Routing) {
                getFruits(kodein.instance())
                getFruit(kodein.instance())
                createFruit(kodein.instance())
                editFruit(kodein.instance())
                deleteFruit(kodein.instance())
            }
        }.start(wait = true)
    }
}

fun main(args: Array<String>) {
    val server = FruitStoreServer()
    server.start()
}
