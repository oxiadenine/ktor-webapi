package ktorwebapi

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import io.ktor.server.engine.applicationEngineEnvironment
import io.ktor.server.engine.connector
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.util.KtorExperimentalAPI
import org.slf4j.LoggerFactory

@KtorExperimentalAPI
fun main(args: Array<String>) {
    val environment = System.getenv("KTOR_ENVIRONMENT") ?: "development"
    val configName = "application.$environment.conf"

    val appEngineEnv = applicationEngineEnvironment {
        config = HoconApplicationConfig(ConfigFactory.load(configName))
        log = LoggerFactory.getLogger("ktor.application")

        connector {
            host = config.property("ktor.deployment.host").getString()
            port = config.property("ktor.deployment.port").getString().toInt()
        }
    }

    embeddedServer(Netty, appEngineEnv).start(wait = true)
}
