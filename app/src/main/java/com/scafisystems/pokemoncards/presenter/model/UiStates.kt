package com.scafisystems.pokemoncards.presenter.model

import com.scafisystems.pokemoncards.domain.entity.PokemonDetails

data class PokemonListUiState(
    val pokemons: List<PokemonDetails> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

data class PokemonDetailsUiState(
    val pokemon: PokemonDetails? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)