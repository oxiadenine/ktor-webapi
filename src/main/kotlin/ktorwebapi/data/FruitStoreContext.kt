package ktorwebapi.data

import org.jetbrains.exposed.sql.Table

object Fruits : Table("fruit") {
    val id = long("id").primaryKey().autoIncrement()
    val no = varchar("no", length = 20).uniqueIndex()
    var description = varchar("description", length = 50)
}


