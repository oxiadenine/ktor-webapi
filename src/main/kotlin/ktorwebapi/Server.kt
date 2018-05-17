package ktorwebapi

import com.typesafe.config.ConfigFactory
import io.ktor.config.HoconApplicationConfig
import io.ktor.server.engine.applicationEngineEnvironment
import io.ktor.server.engine.connector
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.slf4j.LoggerFactory

fun main(args: Array<String>) {
    val environment = HoconApplicationConfig(ConfigFactory.load())
            .property("ktor.deployment.environment").getString()
    val configName = "application.$environment.conf"

    val appEngineEnv = applicationEngineEnvironment {
        log = LoggerFactory.getLogger("ktor.application")
        config = HoconApplicationConfig(ConfigFactory.load(configName))

        module {
            main()
        }

        connector {
            host = config.property("ktor.deployment.host").getString()
            port = config.property("ktor.deployment.port").getString().toInt()
        }
    }

    embeddedServer(Netty, appEngineEnv).start(wait = true)
}