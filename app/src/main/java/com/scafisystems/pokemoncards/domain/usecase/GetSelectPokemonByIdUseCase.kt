package com.scafisystems.pokemoncards.domain.usecase

import com.scafisystems.pokemoncards.domain.datasource.RemotePokemonDataSource
import com.scafisystems.pokemoncards.domain.entity.PokemonDetails


class GetSelectPokemonByIdUseCase(
    private val dataSource: RemotePokemonDataSource
) {
    suspend operator fun invoke(pokemonId: String): PokemonDetails =
        dataSource.fetchPokemonById(pokemonId)
}