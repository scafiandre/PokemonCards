package com.scafisystems.pokemoncards.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scafisystems.pokemoncards.domain.usecase.GetPokemonFavoriteListUseCase
import com.scafisystems.pokemoncards.presenter.model.PokemonListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PokemonFavoritesViewModel @Inject constructor(
    private val getPokemonFavoriteListUseCase: GetPokemonFavoriteListUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonListUiState(isLoading = true))
    val uiState: StateFlow<PokemonListUiState> = _uiState

    fun loadPokemons() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                _uiState.value =
                    PokemonListUiState(pokemons = getPokemonFavoriteListUseCase.invoke())
            } catch (e: Exception) {
                _uiState.value = PokemonListUiState(error = "Erro ao carregar Pokémon.")
            }
        }
    }

    fun retry() {
        loadPokemons()
    }

}