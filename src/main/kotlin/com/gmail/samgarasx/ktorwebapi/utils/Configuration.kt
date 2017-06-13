package com.gmail.samgarasx.ktorwebapi.utils

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser


class Configuration {
    private val root: JsonObject = Parser()
            .parse("src/main/resources/settings.json") as JsonObject

    fun getSection(section: String) = this.root[section] as JsonObject
}