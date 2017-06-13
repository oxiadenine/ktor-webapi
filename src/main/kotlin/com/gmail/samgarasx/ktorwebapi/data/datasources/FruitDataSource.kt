package com.gmail.samgarasx.ktorwebapi.data.datasources

interface FruitDataSource {
    fun initDatabase()
    fun getAll(): Collection<com.gmail.samgarasx.ktorwebapi.models.Fruit>
    fun getById(id: Int): com.gmail.samgarasx.ktorwebapi.models.Fruit
    fun add(fruit: com.gmail.samgarasx.ktorwebapi.models.Fruit): com.gmail.samgarasx.ktorwebapi.models.Fruit
    fun update(id: Int, fruit: com.gmail.samgarasx.ktorwebapi.models.Fruit)
    fun delete(id: Int)
}