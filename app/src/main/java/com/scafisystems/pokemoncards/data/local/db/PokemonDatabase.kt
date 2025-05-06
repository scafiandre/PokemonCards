package com.scafisystems.pokemoncards.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.scafisystems.pokemoncards.data.local.dao.PokemonDao
import com.scafisystems.pokemoncards.data.local.entity.Converters
import com.scafisystems.pokemoncards.data.local.entity.PokemonEntity

@Database(
    entities = [PokemonEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}