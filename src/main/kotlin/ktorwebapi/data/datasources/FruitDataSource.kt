package ktorwebapi.data.datasources

import ktorwebapi.models.Fruit

interface FruitDataSource {
    fun initDatabase()
    fun getAll(): Collection<Fruit>
    fun getById(id: Int): Fruit
    fun add(fruit: Fruit): Fruit
    fun update(id: Int, fruit: Fruit)
    fun delete(id: Int)
}