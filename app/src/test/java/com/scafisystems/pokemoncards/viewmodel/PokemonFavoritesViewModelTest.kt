package com.scafisystems.pokemoncards.viewmodel

import app.cash.turbine.test
import com.scafisystems.pokemoncards.domain.entity.PokemonDetails
import com.scafisystems.pokemoncards.domain.usecase.GetPokemonFavoriteListUseCase
import com.scafisystems.pokemoncards.presenter.viewmodel.PokemonFavoritesViewModel
import com.scafisystems.pokemoncards.util.StandardTestDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonFavoritesViewModelTest {

    @get:Rule
    val dispatcherRule = StandardTestDispatcherRule() // Custom rule for coroutine testing

    private val getPokemonFavoriteListUseCase: GetPokemonFavoriteListUseCase = mock()
    private lateinit var viewModel: PokemonFavoritesViewModel

    @Before
    fun setUp() {
        viewModel = PokemonFavoritesViewModel(getPokemonFavoriteListUseCase)
    }

    @Test
    fun `should emit loading and then pokemons when loadPokemons succeeds`() = runTest {
        val expectedList = listOf(PokemonDetails("pikachu", emptyList(), emptyList()))
        whenever(getPokemonFavoriteListUseCase()).thenReturn(expectedList)

        viewModel.loadPokemons()

        viewModel.uiState.test {
            assertEquals(true, awaitItem().isLoading) // Initial loading
            val result = awaitItem()
            assertEquals(false, result.isLoading)
            assertEquals(expectedList, result.pokemons)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should emit error when loadPokemons fails`() = runTest {
        whenever(getPokemonFavoriteListUseCase()).thenThrow(RuntimeException("Failure"))

        viewModel.loadPokemons()

        viewModel.uiState.test {
            assertEquals(true, awaitItem().isLoading)
            val result = awaitItem()
            assertEquals("Erro ao carregar Pokémon.", result.error)
            assertEquals(false, result.isLoading)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
