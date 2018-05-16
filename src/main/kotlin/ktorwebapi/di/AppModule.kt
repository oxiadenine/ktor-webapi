package ktorwebapi.di

import com.github.salomonbrys.kodein.*
import org.jetbrains.ktor.config.HoconApplicationConfig
import com.typesafe.config.ConfigFactory
import ktorwebapi.controllers.FruitsController
import ktorwebapi.data.datasources.FruitDataSource
import ktorwebapi.data.datasources.FruitDataSourceImpl
import ktorwebapi.data.repositories.FruitRepository
import ktorwebapi.data.repositories.FruitRepositoryImpl
import org.jetbrains.exposed.sql.Database

val appModule = Kodein.Module {
    val config = HoconApplicationConfig(ConfigFactory.load())

    val database = config.config("ktor.database")

    val databaseUrl = database.property("url").getString()
    val databaseDriver = database.property("driver").getString()
    val databaseUser = database.property("user").getString()
    val databasePassword = database.property("password").getString()

    bind<Database>() with instance(Database.connect(databaseUrl, databaseDriver,
            databaseUser, databasePassword))
    bind<FruitDataSource>() with singleton { FruitDataSourceImpl(instance()) }
    bind<FruitRepository>() with singleton { FruitRepositoryImpl(instance()) }
    bind() from provider { FruitsController(instance()) }
}
