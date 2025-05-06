package com.scafisystems.pokemoncards.domain.usecase

import com.scafisystems.pokemoncards.domain.datasource.LocalPokemonDataSource
import com.scafisystems.pokemoncards.domain.entity.PokemonDetails

class GetPokemonByNameLocalUseCase(
    private val localDataSource: LocalPokemonDataSource
) {
    suspend operator fun invoke(pokemonName: String): PokemonDetails =
        localDataSource.getPokemonByName(pokemonName)
}
