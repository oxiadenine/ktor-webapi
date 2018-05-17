package ktorwebapi

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.routing.Routing
import ktorwebapi.controllers.FruitController
import ktorwebapi.data.datasources.FruitDataSource
import ktorwebapi.di.appModule
import ktorwebapi.routing.*
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

fun Application.main() {
    val kodein = Kodein {
        import(appModule)
    }

    val fruitDataSource: FruitDataSource by kodein.instance()
    fruitDataSource.initDatabase()

    val fruitController: FruitController by kodein.instance()

    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }
    install(Routing) {
        getFruits(fruitController)
        getFruit(fruitController)
        createFruit(fruitController)
        editFruit(fruitController)
        deleteFruit(fruitController)
    }
}
