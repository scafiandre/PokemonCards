package com.scafisystems.pokemoncards.domain.usecase

import com.scafisystems.pokemoncards.domain.datasource.LocalPokemonDataSource
import com.scafisystems.pokemoncards.domain.entity.PokemonDetails
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

class GetPokemonFavoriteListUseCaseTest {

    private val localDataSource: LocalPokemonDataSource = mock()
    private val useCase = GetPokemonFavoriteListUseCase(localDataSource)

    @Test
    fun `should return favorite pokemon list from local data source`() = runTest {
        val expected = listOf(PokemonDetails("bulbasaur", emptyList(), emptyList()))
        whenever(localDataSource.getPokemonFavoriteList()).thenReturn(expected)

        val result = useCase()

        assertEquals(expected, result)
        verify(localDataSource).getPokemonFavoriteList()
    }
}