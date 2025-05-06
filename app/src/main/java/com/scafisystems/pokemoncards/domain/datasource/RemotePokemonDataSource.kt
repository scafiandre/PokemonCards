package com.scafisystems.pokemoncards.domain.datasource

import com.scafisystems.pokemoncards.domain.entity.PokemonDetails

interface RemotePokemonDataSource {
    suspend fun fetchPokemonById(pokemonId: String): PokemonDetails
    suspend fun fetchPokemonByName(pokemonName: String): PokemonDetails
    suspend fun fetchPokemonList(): List<PokemonDetails>
    suspend fun fetchPagedPokemonList(offset: Int, limit: Int): List<PokemonDetails>
}