package ktorwebapi.data.repositories

import ktorwebapi.data.datasources.FruitDataSource
import ktorwebapi.models.Fruit

class FruitRepositoryImpl(private val dataSource: FruitDataSource) : FruitRepository {
    override fun getAll(): Collection<Fruit> {
        return this.dataSource.getAll()
    }

    override fun getById(id: Int): Fruit {
        return this.dataSource.getById(id)
    }

    override fun add(fruit: Fruit): Fruit {
        return this.dataSource.add(fruit)
    }

    override fun update(id: Int, fruit: Fruit) {
        this.dataSource.update(id, fruit)
    }

    override fun delete(id: Int) {
        this.dataSource.delete(id)
    }
}