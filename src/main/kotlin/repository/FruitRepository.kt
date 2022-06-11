package repository

import Database
import org.jetbrains.exposed.sql.*

data class FruitEntity(val id: Int, val name: String, val description: String)

object FruitRepository {
    suspend fun read() = runCatching {
        Database.execute {
            FruitTable.selectAll().map { record ->
                FruitEntity(record[FruitTable.id].value, record[FruitTable.name], record[FruitTable.description])
            }
        }
    }

    suspend fun create(name: String, description: String) = runCatching {
        Database.execute<Unit> {
            FruitTable.insert { statement ->
                statement[FruitTable.name] = name
                statement[FruitTable.description] = description
            }
        }
    }

    suspend fun read(id: Int) = runCatching {
        Database.execute {
            FruitTable.select { FruitTable.id eq id }.firstOrNull()?.let { record ->
                FruitEntity(record[FruitTable.id].value, record[FruitTable.name], record[FruitTable.description])
            }
        }
    }

    suspend fun update(id: Int, fields: Map<Column<String>, String>) = runCatching {
        Database.execute<Unit> {
            FruitTable.update({ FruitTable.id eq id }) { statement ->
                fields.forEach { field -> statement[field.key] = field.value }
            }
        }
    }

    suspend fun delete(id: Int) = runCatching {
        Database.execute<Unit> {
            FruitTable.deleteWhere { FruitTable.id eq id }
        }
    }
}