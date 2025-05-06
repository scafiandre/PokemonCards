package com.scafisystems.pokemoncards.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import com.scafisystems.pokemoncards.domain.entity.PokemonDetails
import com.scafisystems.pokemoncards.domain.entity.Stats
import com.scafisystems.pokemoncards.domain.entity.Types

@Entity(tableName = "favorite_pokemon")
data class PokemonEntity(
    @PrimaryKey val name: String,
    val statsJson: String,
    val typesJson: String,
    val image: String?,
)

fun PokemonDetails.toEntity(): PokemonEntity {
    return PokemonEntity(
        name = name,
        statsJson = Gson().toJson(stats),
        typesJson = Gson().toJson(types),
        image = image,
    )
}

fun PokemonEntity.toDomain(): PokemonDetails {
    return PokemonDetails(
        name = name,
        stats = Gson().fromJson(statsJson, Array<Stats>::class.java).toList(),
        types = Gson().fromJson(typesJson, Array<Types>::class.java).toList(),
        image = image
    )
}