package ktor.demo.server.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.slf4j.LoggerFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //FIXME How run ?
//        val environment = System.getenv("KTOR_ENVIRONMENT") ?: "development"
//        val configName = "application.$environment.conf"
//
//        val appEngineEnv = applicationEngineEnvironment {
//            config = HoconApplicationConfig(ConfigFactory.load(configName))
//            log = LoggerFactory.getLogger("ktor.application")
//
//            connector {
//                host = config.property("ktor.deployment.host").getString()
//                port = config.property("ktor.deployment.port").getString().toInt()
//            }
//        }
//
//        embeddedServer(Netty, appEngineEnv).start(wait = true)
    }
    override fun onDestroy() {
        super.onDestroy()
    }
}