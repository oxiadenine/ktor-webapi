package com.gmail.samgarasx.ktorwebapi

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

class FruitStoreServer {
    private val kodein = ConfigurableKodein()

    fun start() {
        this.kodein.addImport(appModule)

        embeddedServer(Netty, 8080) {
            kodein.instance<FruitDataSource>().initDatabase()

            install(DefaultHeaders)
            install(Compression)
            install(CallLogging)

            routing {
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
