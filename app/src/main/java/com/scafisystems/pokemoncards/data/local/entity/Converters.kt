package com.scafisystems.pokemoncards.data.local.entity

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.scafisystems.pokemoncards.domain.entity.Stats
import com.scafisystems.pokemoncards.domain.entity.Types

class Converters {

    @TypeConverter
    fun fromStatsList(value: String): List<Stats> {
        val type = object : TypeToken<List<Stats>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun statsListToString(list: List<Stats>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromTypesList(value: String): List<Types> {
        val type = object : TypeToken<List<Types>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun typesListToString(list: List<Types>): String {
        return Gson().toJson(list)
    }
}
