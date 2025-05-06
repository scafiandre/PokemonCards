package com.scafisystems.pokemoncards.data.repository

import com.scafisystems.pokemoncards.data.local.dao.PokemonDao
import com.scafisystems.pokemoncards.data.local.entity.toDomain
import com.scafisystems.pokemoncards.data.local.entity.toEntity
import com.scafisystems.pokemoncards.domain.datasource.LocalPokemonDataSource
import com.scafisystems.pokemoncards.domain.entity.PokemonDetails

class LocalPokemonRepository(
    private val dao: PokemonDao
) : LocalPokemonDataSource {

    override suspend fun getPokemonByName(pokemonName: String): PokemonDetails {
        return dao.getByName(pokemonName)?.toDomain()
            ?: throw Exception("Pokémon não encontrado")
    }

    override suspend fun getPokemonFavoriteList(): List<PokemonDetails> {
        return dao.getFavorites().map { it.toDomain() }
    }

    override suspend fun setPokemonFavorite(pokemon: PokemonDetails) {
        dao.setFavorite(pokemon.toEntity())
    }

    override suspend fun removePokemonFavorite(pokemon: PokemonDetails) {
        dao.removeFavorite(pokemon.toEntity())
    }
}