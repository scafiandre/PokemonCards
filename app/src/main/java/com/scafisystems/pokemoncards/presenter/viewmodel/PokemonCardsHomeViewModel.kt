package com.scafisystems.pokemoncards.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.scafisystems.pokemoncards.data.remote.paging.PokemonPagingSource
import com.scafisystems.pokemoncards.domain.usecase.GetPagedPokemonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PokemonCardsHomeViewModel @Inject constructor(
    private val getPagedPokemonListUseCase: GetPagedPokemonListUseCase,
) : ViewModel() {

    val pokemonPagingFlow = Pager(
        PagingConfig(pageSize = 15)
    ) {
        PokemonPagingSource(getPagedPokemonListUseCase)
    }.flow.cachedIn(viewModelScope)

}
