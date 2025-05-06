package com.scafisystems.pokemoncards.domain.usecase

import com.scafisystems.pokemoncards.domain.datasource.LocalPokemonDataSource
import com.scafisystems.pokemoncards.domain.entity.PokemonDetails
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class SetPokemonFavoriteUseCaseTest {

    private val localDataSource: LocalPokemonDataSource = mock()
    private val useCase = SetPokemonFavoriteUseCase(localDataSource)

    @Test
    fun `should call set on local data source`() = runTest {
        val pokemon = PokemonDetails("mewtwo", emptyList(), emptyList())

        useCase(pokemon)

        verify(localDataSource).setPokemonFavorite(pokemon)
    }
}
