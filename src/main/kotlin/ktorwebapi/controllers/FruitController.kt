package ktorwebapi.controllers

import com.github.kittinunf.result.Result
import ktorwebapi.data.repositories.FruitRepository
import ktorwebapi.models.Fruit

class FruitController(private val repository: FruitRepository) {
    fun getAll(): Result<Collection<Fruit>, Exception> {
        return Result.of { this.repository.getAll() }
    }

    fun getById(id: Int): Result<Fruit, Exception> {
        return Result.of { this.repository.getById(id) }
    }

    fun add(fruit: Fruit): Result<Fruit, Exception> {
        return Result.of { this.repository.add(fruit) }
    }

    fun update(id: Int, fruit: Fruit): Result<Unit, Exception> {
        return Result.of { this.repository.update(id, fruit) }
    }

    fun delete(id: Int): Result<Unit, Exception> {
        return Result.of { this.repository.delete(id) }
    }
}