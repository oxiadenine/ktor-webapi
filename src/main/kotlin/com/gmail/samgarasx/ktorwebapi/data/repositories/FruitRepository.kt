package com.gmail.samgarasx.ktorwebapi.data.repositories

import com.gmail.samgarasx.ktorwebapi.models.Fruit

interface FruitRepository {
    fun getAll(): Collection<Fruit>
    fun getById(id: Int): Fruit
    fun add(fruit: Fruit): Fruit
    fun update(id: Int, fruit: Fruit)
    fun delete(id: Int)
}