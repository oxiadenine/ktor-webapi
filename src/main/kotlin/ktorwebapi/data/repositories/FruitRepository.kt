package ktorwebapi.data.repositories

import ktorwebapi.models.Fruit

interface FruitRepository {
    fun getAll(): Collection<Fruit>
    fun getById(id: Int): Fruit
    fun add(fruit: Fruit): Fruit
    fun update(id: Int, fruit: Fruit)
    fun delete(id: Int)
}