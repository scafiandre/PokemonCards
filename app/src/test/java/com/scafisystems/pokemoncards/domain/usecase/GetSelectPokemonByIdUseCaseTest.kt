package com.scafisystems.pokemoncards.domain.usecase

import com.scafisystems.pokemoncards.domain.datasource.RemotePokemonDataSource
import com.scafisystems.pokemoncards.domain.entity.PokemonDetails
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

class GetSelectPokemonByIdUseCaseTest {

    private val remoteDataSource: RemotePokemonDataSource = mock()
    private val useCase = GetSelectPokemonByIdUseCase(remoteDataSource)

    @Test
    fun `should return pokemon by id from remote data source`() = runTest {
        val expected = PokemonDetails("squirtle", emptyList(), emptyList())
        whenever(remoteDataSource.fetchPokemonById("7")).thenReturn(expected)

        val result = useCase("7")

        assertEquals(expected, result)
        verify(remoteDataSource).fetchPokemonById("7")
    }
}
