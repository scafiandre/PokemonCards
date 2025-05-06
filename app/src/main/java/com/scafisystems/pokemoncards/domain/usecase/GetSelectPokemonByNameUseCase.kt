package com.scafisystems.pokemoncards.domain.usecase

import com.scafisystems.pokemoncards.domain.datasource.RemotePokemonDataSource
import com.scafisystems.pokemoncards.domain.entity.PokemonDetails


class GetSelectPokemonByNameUseCase(
    private val dataSource: RemotePokemonDataSource
) {
    suspend operator fun invoke(pokemonName: String): PokemonDetails =
        dataSource.fetchPokemonByName(pokemonName)
}