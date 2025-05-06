package com.scafisystems.pokemoncards.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scafisystems.pokemoncards.domain.usecase.GetPokemonFavoriteListUseCase
import com.scafisystems.pokemoncards.domain.usecase.GetSelectPokemonByNameUseCase
import com.scafisystems.pokemoncards.domain.usecase.RemovePokemonFavoriteUseCase
import com.scafisystems.pokemoncards.domain.usecase.SetPokemonFavoriteUseCase
import com.scafisystems.pokemoncards.presenter.model.PokemonDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val getSelectPokemonByNameUseCase: GetSelectPokemonByNameUseCase,
    private val getPokemonFavoriteListUseCase: GetPokemonFavoriteListUseCase,
    private val setPokemonFavoriteUseCase: SetPokemonFavoriteUseCase,
    private val removePokemonFavoriteUseCase: RemovePokemonFavoriteUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonDetailsUiState(isLoading = true))
    val uiState: StateFlow<PokemonDetailsUiState> = _uiState

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite.asStateFlow()

    fun setFavorite(isFavorite: Boolean) {
        _isFavorite.value = isFavorite
        viewModelScope.launch {
            if (isFavorite) {
                _uiState.value.pokemon?.let { setPokemonFavoriteUseCase.invoke(it) }
            } else {
                _uiState.value.pokemon?.let { removePokemonFavoriteUseCase.invoke(it) }
            }
        }
    }

    fun fetchCardDetails(pokemonName: String) {
        viewModelScope.launch {
            _uiState.value =
                PokemonDetailsUiState(pokemon = getSelectPokemonByNameUseCase.invoke(pokemonName))
            _isFavorite.value =
                getPokemonFavoriteListUseCase.invoke().any { it.name == pokemonName }
        }
    }

    fun retry(pokemonName: String) {
        fetchCardDetails(pokemonName)
    }
}
