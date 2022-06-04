package ktorwebapi

import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.serialization.gson.*
import io.ktor.server.routing.*
import io.ktor.util.KtorExperimentalAPI
import ktorwebapi.controllers.FruitController
import ktorwebapi.routing.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.TransactionManager
import java.sql.Connection

@KtorExperimentalAPI
fun Application.main() {
    val appConfig = environment.config

    val databaseUrl = appConfig.property("database.url").getString()
    val databaseDriver = appConfig.property("database.driver").getString()

    Database.connect(databaseUrl, databaseDriver)
    TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE

    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }
    install(Routing) {
        api()
        fruits(FruitController())
    }
}
