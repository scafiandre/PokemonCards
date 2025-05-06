package com.scafisystems.pokemoncards.domain.usecase

import com.scafisystems.pokemoncards.domain.datasource.RemotePokemonDataSource
import com.scafisystems.pokemoncards.domain.entity.PokemonDetails
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

class GetPokemonListUseCaseTest {

    private val remoteDataSource: RemotePokemonDataSource = mock()
    private val useCase = GetPokemonListUseCase(remoteDataSource)

    @Test
    fun `should return pokemon list from remote data source`() = runTest {
        val expected = listOf(PokemonDetails("charmander", emptyList(), emptyList()))
        whenever(remoteDataSource.fetchPokemonList()).thenReturn(expected)

        val result = useCase()

        assertEquals(expected, result)
        verify(remoteDataSource).fetchPokemonList()
    }
}
