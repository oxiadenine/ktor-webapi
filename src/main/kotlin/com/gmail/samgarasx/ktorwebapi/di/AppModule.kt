package com.gmail.samgarasx.ktorwebapi.di

import com.github.salomonbrys.kodein.*
import com.gmail.samgarasx.ktorwebapi.controllers.FruitsController
import com.gmail.samgarasx.ktorwebapi.data.datasources.FruitDataSource
import com.gmail.samgarasx.ktorwebapi.data.datasources.FruitDataSourceImpl
import com.gmail.samgarasx.ktorwebapi.data.repositories.FruitRepository
import com.gmail.samgarasx.ktorwebapi.data.repositories.FruitRepositoryImpl
import org.jetbrains.exposed.sql.Database

import com.typesafe.config.ConfigFactory

val appModule = Kodein.Module {
    val config = ConfigFactory.load()

    val databaseConnection = config.getConfig("ktor.databaseConnection")

    val databaseUrl = databaseConnection.getString("url")
    val databaseDriver = databaseConnection.getString("driver")
    val databaseUser = databaseConnection.getString("user")
    val databasePassword = databaseConnection.getString("password")

    bind<Database>() with instance(Database.connect(databaseUrl, databaseDriver,
            databaseUser, databasePassword))
    bind<FruitDataSource>() with singleton { FruitDataSourceImpl(instance()) }
    bind<FruitRepository>() with singleton { FruitRepositoryImpl(instance()) }
    bind() from provider { FruitsController(instance()) }
}
