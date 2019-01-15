package ktorwebapi.controllers

import ktorwebapi.entities.FruitEntity
import ktorwebapi.entities.Fruits
import ktorwebapi.models.Fruit
import org.jetbrains.exposed.sql.transactions.transaction

class FruitController {
    fun all() = transaction {
        return@transaction FruitEntity.all().map {
            Fruit(it.id.value, it.no, it.description)
        }.toList()
    }

    fun one(id: Int) = transaction {
        val fruitEntity = FruitEntity.findById(id)

        return@transaction if (fruitEntity != null) {
            Fruit(fruitEntity.id.value, fruitEntity.no, fruitEntity.description)
        } else null
    }

    fun new(fruit: Fruit) = transaction {
        var fruitEntity = FruitEntity.find { Fruits.no eq fruit.no }.toList().singleOrNull()

        return@transaction if (fruitEntity == null) {
            fruitEntity = FruitEntity.new {
                no = fruit.no
                description = fruit.description
            }

            Fruit(fruitEntity.id.value, fruitEntity.no, fruitEntity.description)
        } else null
    }

    fun edit(id: Int, fruit: Fruit) = transaction {
        val fruitEntity = FruitEntity.findById(id)

        return@transaction if (fruitEntity != null) {
            fruitEntity.description = fruit.description

            Fruit(fruitEntity.id.value, fruitEntity.no, fruitEntity.description)
        } else null
    }

    fun delete(id: Int) = transaction {
        val fruitEntity = FruitEntity.findById(id)

        return@transaction if (fruitEntity != null) {
            fruitEntity.delete()

            Fruit(fruitEntity.id.value, fruitEntity.no, fruitEntity.description)
        } else null
    }
}
