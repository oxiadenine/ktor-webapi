package ktorwebapi.controllers

import ktorwebapi.data.Fruits
import ktorwebapi.models.Fruit
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class FruitController {
    fun all(): Collection<Fruit> {
        return transaction {
            return@transaction Fruits.selectAll().map {
                Fruit(it[Fruits.id], it[Fruits.no], it[Fruits.description])
            }
        }
    }

    fun one(id: Long): Fruit? {
        return transaction {
            return@transaction Fruits.select { Fruits.id eq id }.map {
                Fruit(it[Fruits.id], it[Fruits.no], it[Fruits.description])
            }.singleOrNull()
        }
    }

    fun new(fruit: Fruit): Fruit? {
        return transaction {
            val oldFruit = Fruits.select { Fruits.no eq fruit.no }.map {
                Fruit(it[Fruits.id], it[Fruits.no], it[Fruits.description])
            }.singleOrNull()

            println(oldFruit)

            if (oldFruit != null) {
                return@transaction null
            }

            fruit.id = Fruits.insert {
                it[no] = fruit.no
                it[description] = fruit.description
            }.generatedKey!!.toLong()

            return@transaction fruit
        }
    }

    fun edit(id: Long, fruit: Fruit): Fruit? {
        return transaction {
            val oldFruit = Fruits.select { Fruits.id eq id }.map {
                Fruit(it[Fruits.id], it[Fruits.no], it[Fruits.description])
            }.singleOrNull()

            if (oldFruit != null) {
                Fruits.update({ Fruits.id eq id }) {
                    it[no] = fruit.no
                    it[description] = fruit.description
                }

                fruit.id = id

                return@transaction fruit
            }

            return@transaction oldFruit
        }
    }

    fun delete(id: Long): Fruit? {
        return transaction {
            val oldFruit = Fruits.select { Fruits.id eq id }.map {
                Fruit(it[Fruits.id], it[Fruits.no], it[Fruits.description])
            }.singleOrNull()

            if (oldFruit != null) {
                Fruits.deleteWhere { Fruits.id eq id }
            }

            return@transaction oldFruit
        }
    }
}