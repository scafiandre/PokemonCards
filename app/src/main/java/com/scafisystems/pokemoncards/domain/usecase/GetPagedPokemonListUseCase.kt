package com.scafisystems.pokemoncards.domain.usecase

import com.scafisystems.pokemoncards.domain.datasource.RemotePokemonDataSource
import com.scafisystems.pokemoncards.domain.entity.PokemonDetails


class GetPagedPokemonListUseCase(
    private val dataSource: RemotePokemonDataSource,
) {
    suspend operator fun invoke(offset: Int, limit: Int): List<PokemonDetails> =
        dataSource.fetchPagedPokemonList(offset, limit)
}