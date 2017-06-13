package com.gmail.samgarasx.ktorwebapi

import com.gmail.samgarasx.ktorwebapi.controllers.FruitsController
import com.gmail.samgarasx.ktorwebapi.data.datasources.FruitDataSourceImpl
import com.gmail.samgarasx.ktorwebapi.data.repositories.FruitRepositoryImpl
import com.gmail.samgarasx.ktorwebapi.routing.*
import com.mchange.v2.c3p0.ComboPooledDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.features.Compression
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.host.embeddedServer
import org.jetbrains.ktor.logging.CallLogging
import org.jetbrains.ktor.netty.Netty
import org.jetbrains.ktor.routing.*
import org.postgresql.Driver


class FruitStoreServer {
    val pool = ComboPooledDataSource().apply {
        driverClass = Driver::class.java.name
        jdbcUrl = "jdbc:postgresql://localhost:5432/<your_database>"
        user = "<your_user>"
        password = "<your_password>"
    }

    val dataSource = FruitDataSourceImpl(Database.connect(pool))
    val repository = FruitRepositoryImpl(dataSource)
    val controller = FruitsController(repository)

    fun start() {
        embeddedServer(Netty, 8080) {
            dataSource.initDatabase()
            environment.monitor.applicationStopped += { pool.close() }

            install(DefaultHeaders)
            install(Compression)
            install(CallLogging)

            routing {
                getFruits(controller)
                getFruit(controller)
                createFruit(controller)
                editFruit(controller)
                deleteFruit(controller)
            }
        }.start(wait = true)
    }
}

fun main(args: Array<String>) {
    val server = FruitStoreServer()
    server.start()
}
