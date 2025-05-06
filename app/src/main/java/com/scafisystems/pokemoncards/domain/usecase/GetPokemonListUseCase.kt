package com.scafisystems.pokemoncards.domain.usecase

import com.scafisystems.pokemoncards.domain.datasource.RemotePokemonDataSource
import com.scafisystems.pokemoncards.domain.entity.PokemonDetails


class GetPokemonListUseCase(
    private val dataSource: RemotePokemonDataSource,
) {
    suspend operator fun invoke(): List<PokemonDetails> = dataSource.fetchPokemonList()
}