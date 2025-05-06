package com.scafisystems.pokemoncards.data.repository

import com.scafisystems.pokemoncards.data.remote.api.reqApi
import com.scafisystems.pokemoncards.data.remote.entity.mapToPokemonDetails
import com.scafisystems.pokemoncards.domain.datasource.RemotePokemonDataSource
import com.scafisystems.pokemoncards.domain.entity.PokemonDetails

class RemotePokemonRepository() : RemotePokemonDataSource {

    override suspend fun fetchPagedPokemonList(offset: Int, limit: Int): List<PokemonDetails> {
        val listDao = reqApi.getListPokemons(offset, limit)

        return listDao.listOfPokemon.map {
            try {
                fetchPokemonByName(it.name)
            } catch (e: Exception) {
                PokemonDetails(
                    name = it.name,
                    stats = emptyList(),
                    types = emptyList(),
                    image = ""
                )
            }
        }
    }

    override suspend fun fetchPokemonList(): List<PokemonDetails> {
        return reqApi.getListPokemons().listOfPokemon.map {
            try {
                fetchPokemonByName(it.name)
            } catch (e: Exception) {
                PokemonDetails(
                    name = it.name,
                    stats = emptyList(),
                    types = emptyList(),
                    image = ""
                )
            }
        }
    }

    override suspend fun fetchPokemonById(pokemonId: String): PokemonDetails {
        return reqApi.getPokemonById(pokemonId).mapToPokemonDetails()
    }

    override suspend fun fetchPokemonByName(pokemonName: String): PokemonDetails {
        return reqApi.getPokemonByName(pokemonName).mapToPokemonDetails()

    }
}
