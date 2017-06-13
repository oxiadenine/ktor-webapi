package com.gmail.samgarasx.ktorwebapi.data.repositories

import com.gmail.samgarasx.ktorwebapi.data.datasources.FruitDataSource
import com.gmail.samgarasx.ktorwebapi.models.Fruit

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