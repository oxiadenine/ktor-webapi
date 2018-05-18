package ktorwebapi.data.repositories

import ktorwebapi.data.Fruits
import ktorwebapi.models.Fruit
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

class FruitRepository {
    fun findAll(): Collection<Fruit> {
        return transaction {
            return@transaction ktorwebapi.data.Fruits.selectAll().map {
                Fruit(it[Fruits.id], it[Fruits.no], it[Fruits.description]
                        ?: "")
            }
        }
    }

    fun findById(id: Int): Fruit? {
        return transaction {
            return@transaction Fruits.select(Fruits.id eq id).map {
                Fruit(it[Fruits.id], it[Fruits.no], it[Fruits.description]
                        ?: "")
            }.singleOrNull()
        }
    }

    fun create(fruit: Fruit): Fruit {
        return transaction {
            val id = Fruits.insert {
                it[no] = fruit.no
                it[description] = fruit.description
            }.generatedKey!!.toInt()

            return@transaction Fruit(id, fruit.no, fruit.description)
        }
    }

    fun update(fruit: Fruit) {
        transaction {
            Fruits.update({ Fruits.id eq fruit.id }) {
                it[no] = fruit.no
                it[description] = fruit.description
            }
        }
    }

    fun delete(id: Int) {
        transaction {
            Fruits.deleteWhere { Fruits.id eq id }
        }
    }
}