package com.scafisystems.pokemoncards.domain.usecase

import com.scafisystems.pokemoncards.domain.datasource.LocalPokemonDataSource
import com.scafisystems.pokemoncards.domain.entity.PokemonDetails

class GetPokemonFavoriteListUseCase(
    private val localDataSource: LocalPokemonDataSource
) {
    suspend operator fun invoke(): List<PokemonDetails> =
        localDataSource.getPokemonFavoriteList()
}
