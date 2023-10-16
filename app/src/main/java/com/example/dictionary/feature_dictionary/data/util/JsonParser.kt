package com.example.dictionary.feature_dictionary.data.util

import java.lang.reflect.Type

//we need a parser to convert the list of meaning to string if we have more than one meaning
interface JsonParser {
    fun <T> fromJson(json: String, type: Type): T?//the type is from java

    fun <T> toJson(obj: T, type: Type): String?
}