package com.gmail.samgarasx.ktorwebapi.controllers

import com.beust.klaxon.JsonObject
import com.beust.klaxon.json
import com.github.kittinunf.result.Result
import com.gmail.samgarasx.ktorwebapi.data.repositories.FruitRepository
import com.gmail.samgarasx.ktorwebapi.models.Fruit

class FruitsController(private val repository: FruitRepository) {
    fun getAll(): JsonObject {
        Result.of { this.repository.getAll() }
                .fold({ fruits ->
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
                }, { error ->
                    return json {
                        obj(
                                "ok" to false,
                                "msg" to error.message
                        )
                    }
                })
    }

    fun getById(id: Int): JsonObject {
        Result.of { this.repository.getById(id) }
                .fold({ (id, no, description) ->
                    return json {
                        obj(
                                "ok" to true,
                                "msg" to obj(
                                        "id" to id,
                                        "no" to no,
                                        "description" to description
                                )
                        )
                    }
                }, { _ -> return json { obj() } })
    }

    fun add(fruit: Fruit): JsonObject {
        Result.of { this.repository.add(fruit) }
                .fold({ (id, no, description) ->
                    return json {
                        obj(
                                "ok" to true,
                                "msg" to obj(
                                        "id" to id,
                                        "no" to no,
                                        "description" to description
                                )
                        )
                    }
                }, { error ->
                    return json {
                        obj(
                                "ok" to false,
                                "msg" to error.message
                        )
                    }
                })
    }

    fun update(id: Int, fruit: Fruit): JsonObject {
        Result.of { this.repository.update(id, fruit) }
                .fold({ return json { obj() } }, { error ->
                    return json {
                        obj(
                                "ok" to false,
                                "msg" to error.message
                        )
                    }
                })
    }

    fun delete(id: Int): JsonObject {
        Result.of { this.repository.delete(id) }
                .fold({ return json { obj() } }, { error ->
                    return json {
                        obj(
                                "ok" to false,
                                "msg" to error.message
                        )
                    }
                })
    }
}