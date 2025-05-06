package com.scafisystems.pokemoncards.domain.usecase

import org.junit.Assert.*

import com.scafisystems.pokemoncards.domain.datasource.RemotePokemonDataSource
import com.scafisystems.pokemoncards.domain.entity.PokemonDetails
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.mockito.kotlin.*

class GetPagedPokemonListUseCaseTest {

    private val remotePokemonDataSource: RemotePokemonDataSource = mock()
    private val getPagedPokemonListUseCase = GetPagedPokemonListUseCase(remotePokemonDataSource)

    @Test
    fun `test getPagedPokemonListUseCase returns expected pokemon list`(): Unit = runTest {
        // Arrange
        val expectedPokemonList = listOf(
            PokemonDetails("pikachu", emptyList(), emptyList(), ""),
            PokemonDetails("bulbasaur", emptyList(), emptyList(), "")
        )
        whenever(remotePokemonDataSource.fetchPagedPokemonList(0, 15)).thenReturn(
            expectedPokemonList
        )

        // Act
        val result = getPagedPokemonListUseCase(0, 15)

        // Assert
        assertEquals(expectedPokemonList, result)
        verify(remotePokemonDataSource).fetchPagedPokemonList(0, 15)
    }
}