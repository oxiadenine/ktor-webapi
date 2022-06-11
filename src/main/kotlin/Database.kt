import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object FruitTable : IntIdTable("fruit") {
    val name = varchar("name", 32).index()
    val description = text("description")
}

object Database {
    private lateinit var connection: Database

    fun connect(config: ApplicationConfig) {
        val hikariConfig = HikariConfig().apply {
            jdbcUrl = config.property("url").getString()
            driverClassName = config.property("driver").getString()
            username = config.property("username").getString()
            password = config.property("password").getString()

            validate()
        }

        connection = Database.connect(HikariDataSource(hikariConfig))

        transaction(connection) {
            SchemaUtils.create(FruitTable)
        }
    }

    suspend fun <T> execute(block: suspend () -> T) = newSuspendedTransaction(Dispatchers.IO, connection) { block() }
}