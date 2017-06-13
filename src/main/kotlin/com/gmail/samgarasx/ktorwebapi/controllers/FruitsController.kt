package com.gmail.samgarasx.ktorwebapi.controllers

import com.beust.klaxon.JsonObject
import com.beust.klaxon.json
import com.gmail.samgarasx.ktorwebapi.data.repositories.FruitRepository
import com.gmail.samgarasx.ktorwebapi.models.Fruit


class FruitsController(private val repository: FruitRepository) {
    fun getAll(): JsonObject {
        val fruits = this.repository.getAll()
        return json {
            obj(
                    "ok" to true,
                    "msg" to array(fruits.map {
                        obj(
                            "id" to it.id,
                            "no" to it.no,
                            "description" to it.description
                        )
                    })
            )
        }
    }

    fun getById(id: Int): JsonObject {
        val fruit = this.repository.getById(id) ?: return json { obj() }
        return json {
            obj(
                    "ok" to true,
                    "msg" to obj(
                            "id" to fruit.id,
                            "no" to fruit.no,
                            "description" to fruit.description
                    )
            )
        }
    }

    fun add(fruit: Fruit): JsonObject {
        val newFruit = this.repository.add(fruit)
        return json {
            obj(
                    "ok" to true,
                    "msg" to obj(
                            "id" to newFruit.id,
                            "no" to newFruit.no,
                            "description" to newFruit.description
                    )
            )
        }
    }

    fun update(id: Int, fruit: Fruit) {
        this.repository.update(id, fruit)
    }

    fun delete(id: Int) {
        this.repository.delete(id)
    }
}