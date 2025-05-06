package com.scafisystems.pokemoncards.domain.usecase

import com.scafisystems.pokemoncards.domain.datasource.LocalPokemonDataSource
import com.scafisystems.pokemoncards.domain.entity.PokemonDetails
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class RemovePokemonFavoriteUseCaseTest {

    private val localDataSource: LocalPokemonDataSource = mock()
    private val useCase = RemovePokemonFavoriteUseCase(localDataSource)

    @Test
    fun `should call remove on local data source`() = runTest {
        val pokemon = PokemonDetails("snorlax", emptyList(), emptyList())

        useCase(pokemon)

        verify(localDataSource).removePokemonFavorite(pokemon)
    }
}
