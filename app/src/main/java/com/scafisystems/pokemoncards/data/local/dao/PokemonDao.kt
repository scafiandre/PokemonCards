package com.scafisystems.pokemoncards.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.scafisystems.pokemoncards.data.local.entity.PokemonEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM favorite_pokemon WHERE name = :name")
    suspend fun getByName(name: String): PokemonEntity?

    @Query("SELECT * FROM favorite_pokemon")
    suspend fun getFavorites(): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setFavorite(pokemon: PokemonEntity)

    @Delete
    suspend fun removeFavorite(pokemon: PokemonEntity)

}
