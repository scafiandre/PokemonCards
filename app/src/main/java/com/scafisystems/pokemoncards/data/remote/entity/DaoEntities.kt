package com.scafisystems.pokemoncards.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.scafisystems.pokemoncards.domain.entity.PokemonDetails
import com.scafisystems.pokemoncards.domain.entity.PokemonListDetails
import com.scafisystems.pokemoncards.domain.entity.Stats
import com.scafisystems.pokemoncards.domain.entity.Types

data class PokemonListDao(
    @SerializedName("count")
    val numberOfPokemon: Int,
    @SerializedName("results")
    val listOfPokemon: List<PokemonListDetailsDao>
)

data class PokemonListDetailsDao(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

data class PokemonDetailsDao(
    @SerializedName("name")
    val name: String,

    @SerializedName("stats")
    val stats: List<StatsDao>,

    @SerializedName("types")
    val types: List<TypesDao>,

    @SerializedName("sprites")
    val image: SpritesDao
)

data class TypesDao(
    @SerializedName("slot")
    val slot: Int? = null,

    @SerializedName("type")
    val type: PokemonListDetails? = null
)

data class SpritesDao(
    @SerializedName("front_default")
    val frontImage: String? = null,

    @SerializedName("back_default")
    val backImage: String? = null
)

data class StatsDao(
    @SerializedName("base_stat")
    val baseStat: Int? = null,

    @SerializedName("effort")
    val effort: Int? = null,

    @SerializedName("stat")
    val stat: PokemonListDetails? = null
)

fun PokemonDetailsDao.mapToPokemonDetails(): PokemonDetails {
    return PokemonDetails(
        name = this.name,
        stats = this.stats.map {
            Stats(
                baseStat = it.baseStat,
                effort = it.effort,
                stat = it.stat
            )
        },
        types = this.types.map {
            Types(
                slot = it.slot,
                type = it.type
            )
        },
        image = this.image.frontImage
    )
}