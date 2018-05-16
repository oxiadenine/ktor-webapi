package ktorwebapi.data.datasources

import ktorwebapi.data.Fruits
import ktorwebapi.models.Fruit
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class FruitDataSourceImpl(private val db: Database) : FruitDataSource {
    override fun initDatabase() {
        transaction {
            logger.addLogger(StdOutSqlLogger)
            SchemaUtils.create(Fruits)
        }
    }

    override fun getAll(): Collection<Fruit> {
        return transaction {
            return@transaction Fruits.selectAll().map {
                Fruit(it[Fruits.id], it[Fruits.no], it[Fruits.description]
                        ?: "")
            }
        }
    }

    override fun getById(id: Int): Fruit {
        return transaction {
            return@transaction Fruits.select(Fruits.id eq id).map {
                Fruit(it[Fruits.id], it[Fruits.no], it[Fruits.description]
                        ?: "")
            }.single()
        }
    }

    override fun add(fruit: Fruit): Fruit {
        return transaction {
            val id = Fruits.insert {
                it[no] = fruit.no
                it[description] = fruit.description
            }.generatedKey!!.toInt()

            return@transaction Fruit(id, fruit.no, fruit.description)
        }
    }

    override fun update(id: Int, fruit: Fruit) {
        transaction {
            Fruits.update({ Fruits.id eq id }) {
                it[no] = fruit.no
                it[description] = fruit.description
            }
        }
    }

    override fun delete(id: Int) {
        transaction {
            Fruits.deleteWhere { Fruits.id eq id }
        }
    }
}