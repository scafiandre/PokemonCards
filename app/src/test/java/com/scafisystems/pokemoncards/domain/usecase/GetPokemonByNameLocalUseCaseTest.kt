package com.scafisystems.pokemoncards.domain.usecase

import com.scafisystems.pokemoncards.domain.datasource.LocalPokemonDataSource
import com.scafisystems.pokemoncards.domain.entity.PokemonDetails
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

class GetPokemonByNameLocalUseCaseTest {

    private val localDataSource: LocalPokemonDataSource = mock()
    private val useCase = GetPokemonByNameLocalUseCase(localDataSource)

    @Test
    fun `should return pokemon by name from local data source`() = runTest {
        val expected = PokemonDetails("pikachu", emptyList(), emptyList())
        whenever(localDataSource.getPokemonByName("pikachu")).thenReturn(expected)

        val result = useCase("pikachu")

        assertEquals(expected, result)
        verify(localDataSource).getPokemonByName("pikachu")
    }
}
