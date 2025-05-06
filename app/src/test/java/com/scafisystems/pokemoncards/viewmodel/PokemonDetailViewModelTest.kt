package com.scafisystems.pokemoncards.viewmodel

import app.cash.turbine.test
import com.scafisystems.pokemoncards.domain.entity.PokemonDetails
import com.scafisystems.pokemoncards.domain.usecase.GetPokemonFavoriteListUseCase
import com.scafisystems.pokemoncards.domain.usecase.GetSelectPokemonByNameUseCase
import com.scafisystems.pokemoncards.domain.usecase.RemovePokemonFavoriteUseCase
import com.scafisystems.pokemoncards.domain.usecase.SetPokemonFavoriteUseCase
import com.scafisystems.pokemoncards.presenter.viewmodel.PokemonDetailViewModel
import com.scafisystems.pokemoncards.util.StandardTestDispatcherRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonDetailViewModelTest {

    @get:Rule
    val dispatcherRule = StandardTestDispatcherRule()

    private val getSelectPokemonByNameUseCase: GetSelectPokemonByNameUseCase = mock()
    private val getPokemonFavoriteListUseCase: GetPokemonFavoriteListUseCase = mock()
    private val setPokemonFavoriteUseCase: SetPokemonFavoriteUseCase = mock()
    private val removePokemonFavoriteUseCase: RemovePokemonFavoriteUseCase = mock()

    private lateinit var viewModel: PokemonDetailViewModel

    @Before
    fun setup() {
        viewModel = PokemonDetailViewModel(
            getSelectPokemonByNameUseCase,
            getPokemonFavoriteListUseCase,
            setPokemonFavoriteUseCase,
            removePokemonFavoriteUseCase
        )
    }

    @Test
    fun `should load pokemon details and update favorite state`() = runTest {
        val pokemonName = "charizard"
        val pokemon = PokemonDetails(name = pokemonName, stats = emptyList(), types = emptyList())

        // Certifique-se que o mock está correto
        whenever(getSelectPokemonByNameUseCase(pokemonName)).thenReturn(pokemon)
        whenever(getPokemonFavoriteListUseCase()).thenReturn(listOf(pokemon))

        // Ação
        viewModel.fetchCardDetails(pokemonName)
        advanceUntilIdle() // Aguarda todas as coroutines terminarem

        // Validação do UI State
        viewModel.uiState.test {
            val state = awaitItem()
            println("Esperado: $pokemon")
            println("Atual: ${state.pokemon}")
            assertEquals(pokemon, state.pokemon)
            cancelAndIgnoreRemainingEvents()
        }

        // Validação do estado de favorito
        viewModel.isFavorite.test {
            assertEquals(true, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should call setPokemonFavorite when setFavorite is true`() = runTest {
        val pokemon = PokemonDetails("bulbasaur", emptyList(), emptyList())
        viewModel = PokemonDetailViewModel(
            getSelectPokemonByNameUseCase,
            getPokemonFavoriteListUseCase,
            setPokemonFavoriteUseCase,
            removePokemonFavoriteUseCase
        )

        viewModel.fetchCardDetails(pokemon.name)
        whenever(getSelectPokemonByNameUseCase(pokemon.name)).thenReturn(pokemon)
        whenever(getPokemonFavoriteListUseCase()).thenReturn(emptyList())

        // Force state to have the loaded Pokémon
        viewModel.fetchCardDetails(pokemon.name)
        advanceUntilIdle() // wait for coroutine

        viewModel.setFavorite(true)
        advanceUntilIdle()

        verify(setPokemonFavoriteUseCase).invoke(pokemon)
        verify(removePokemonFavoriteUseCase, never()).invoke(any())
    }

    @Test
    fun `should call removePokemonFavorite when setFavorite is false`() = runTest {
        val pokemon = PokemonDetails("mew", emptyList(), emptyList())
        whenever(getSelectPokemonByNameUseCase(pokemon.name)).thenReturn(pokemon)
        whenever(getPokemonFavoriteListUseCase()).thenReturn(listOf(pokemon))

        viewModel.fetchCardDetails(pokemon.name)
        advanceUntilIdle()

        viewModel.setFavorite(false)
        advanceUntilIdle()

        verify(removePokemonFavoriteUseCase).invoke(pokemon)
        verify(setPokemonFavoriteUseCase, never()).invoke(any())
    }
}
