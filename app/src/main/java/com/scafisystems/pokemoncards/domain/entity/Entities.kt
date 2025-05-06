package com.scafisystems.pokemoncards.domain.entity

data class PokemonListDetails(
    val name: String,
    val url: String
)

data class PokemonDetails(
    val name: String,
    val stats: List<Stats>,
    val types: List<Types>,
    val image: String? = null
)

data class Stats(
    val baseStat: Int? = null,
    val effort: Int? = null,
    val stat: PokemonListDetails? = null
)

data class Types(
    val slot: Int? = null,
    val type: PokemonListDetails? = null
)

data class PokemonItem(
    val imageResource: String? = null,
    val name: String
)