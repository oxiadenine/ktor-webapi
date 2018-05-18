package ktorwebapi.controllers

import ktorwebapi.data.repositories.FruitRepository
import ktorwebapi.models.Fruit

class FruitController(private val repository: FruitRepository) {
    fun getAll(): Collection<Fruit> {
        return this.repository.findAll()
    }

    fun getById(id: Int): Fruit? {
        return this.repository.findById(id)
    }

    fun add(fruit: Fruit): Fruit {
        return this.repository.create(fruit)
    }

    fun update(id: Int, fruit: Fruit): Fruit? {
        val oldFruit = this.repository.findById(id)

        oldFruit?.apply {
            oldFruit.no = fruit.no
            oldFruit.description = fruit.description

            this@FruitController.repository.update(oldFruit)
        }

        return oldFruit
    }

    fun delete(id: Int): Fruit? {
        val oldFruit = this.repository.findById(id)

        oldFruit?.apply {
            this@FruitController.repository.delete(id)
        }

        return oldFruit
    }
}