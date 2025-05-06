package com.scafisystems.pokemoncards.domain.usecase

import com.scafisystems.pokemoncards.domain.datasource.RemotePokemonDataSource
import com.scafisystems.pokemoncards.domain.entity.PokemonDetails
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

class GetSelectPokemonByNameUseCaseTest {

    private val remoteDataSource: RemotePokemonDataSource = mock()
    private val useCase = GetSelectPokemonByNameUseCase(remoteDataSource)

    @Test
    fun `should return pokemon by name from remote data source`() = runTest {
        val expected = PokemonDetails("eevee", emptyList(), emptyList())
        whenever(remoteDataSource.fetchPokemonByName("eevee")).thenReturn(expected)

        val result = useCase("eevee")

        assertEquals(expected, result)
        verify(remoteDataSource).fetchPokemonByName("eevee")
    }
}
