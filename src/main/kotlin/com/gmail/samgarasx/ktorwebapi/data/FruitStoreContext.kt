package com.gmail.samgarasx.ktorwebapi.data

import org.jetbrains.exposed.sql.Table

object Fruits : Table() {
    val id = integer("id").primaryKey().autoIncrement()
    val no = varchar("no", length = 20).uniqueIndex()
    var description = varchar("description", length = 50).nullable()
}


