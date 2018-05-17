package ktorwebapi.di

import com.typesafe.config.ConfigFactory
import io.ktor.config.HoconApplicationConfig
import ktorwebapi.controllers.FruitController
import ktorwebapi.data.datasources.FruitDataSource
import ktorwebapi.data.repositories.FruitRepository
import org.jetbrains.exposed.sql.Database
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val appModule = Kodein.Module {
    val environment = HoconApplicationConfig(ConfigFactory.load())
            .property("ktor.deployment.environment").getString()
    val configName = "application.$environment.conf"

    val config = HoconApplicationConfig(ConfigFactory.load(configName))

    val databaseConfig = config.config("ktor.database")

    val databaseUrl = databaseConfig.property("url").getString()
    val databaseDriver = databaseConfig.property("driver").getString()
    val databaseUser = databaseConfig.property("user").getString()
    val databasePassword = databaseConfig.property("password").getString()

    bind<Database>() with instance(Database.connect(databaseUrl, databaseDriver,
            databaseUser, databasePassword))
    bind<FruitDataSource>() with singleton { FruitDataSource() }
    bind<FruitRepository>() with singleton { FruitRepository(instance()) }
    bind() from provider { FruitController(instance()) }
}
