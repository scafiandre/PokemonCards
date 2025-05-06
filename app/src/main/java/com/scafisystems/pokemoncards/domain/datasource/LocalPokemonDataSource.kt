package com.scafisystems.pokemoncards.domain.datasource

import com.scafisystems.pokemoncards.domain.entity.PokemonDetails

interface LocalPokemonDataSource {

    suspend fun getPokemonByName(pokemonName: String): PokemonDetails
    suspend fun getPokemonFavoriteList(): List<PokemonDetails>
    suspend fun setPokemonFavorite(pokemon: PokemonDetails)
    suspend fun removePokemonFavorite(pokemon: PokemonDetails)
}