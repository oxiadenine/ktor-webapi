package ktorwebapi.data.repositories

import ktorwebapi.data.datasources.FruitDataSource
import ktorwebapi.models.Fruit

class FruitRepository(private val dataSource: FruitDataSource) {
    fun getAll(): Collection<Fruit> {
        return this.dataSource.getAll()
    }

    fun getById(id: Int): Fruit {
        return this.dataSource.getById(id)
    }

    fun add(fruit: Fruit): Fruit {
        return this.dataSource.add(fruit)
    }

    fun update(id: Int, fruit: Fruit) {
        this.dataSource.update(id, fruit)
    }

    fun delete(id: Int) {
        this.dataSource.delete(id)
    }
}