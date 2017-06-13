package com.gmail.samgarasx.ktorwebapi.di

import com.github.salomonbrys.kodein.*
import com.gmail.samgarasx.ktorwebapi.controllers.FruitsController
import com.gmail.samgarasx.ktorwebapi.data.datasources.FruitDataSource
import com.gmail.samgarasx.ktorwebapi.data.datasources.FruitDataSourceImpl
import com.gmail.samgarasx.ktorwebapi.data.repositories.FruitRepository
import com.gmail.samgarasx.ktorwebapi.data.repositories.FruitRepositoryImpl
import com.gmail.samgarasx.ktorwebapi.utils.Configuration
import org.jetbrains.exposed.sql.Database

val appModule = Kodein.Module {
    val databaseSection = Configuration().getSection("database")

    val databaseUrl = databaseSection["url"] as String
    val databaseDriver = databaseSection["driver"] as String
    val databaseUser = databaseSection["user"] as String
    val databasePassword = databaseSection["password"] as String

    bind<Database>() with instance(Database.connect(databaseUrl, databaseDriver,
            databaseUser, databasePassword))
    bind<FruitDataSource>() with singleton { FruitDataSourceImpl(instance()) }
    bind<FruitRepository>() with singleton { FruitRepositoryImpl(instance()) }
    bind() from provider { FruitsController(instance()) }
}
