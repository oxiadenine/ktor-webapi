package ktorwebapi.entities

import org.jetbrains.exposed.dao.*

object Fruits : IntIdTable("fruit") {
    val no = Fruits.text("no").uniqueIndex()
    val description = Fruits.text("description")
}

class FruitEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<FruitEntity>(Fruits)

    var no by Fruits.no
    var description by Fruits.description
}
