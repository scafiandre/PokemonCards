package com.scafisystems.pokemoncards.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.scafisystems.pokemoncards.domain.entity.PokemonDetails
import com.scafisystems.pokemoncards.domain.usecase.GetPagedPokemonListUseCase

class PokemonPagingSource(
    private val useCase: GetPagedPokemonListUseCase
) : PagingSource<Int, PokemonDetails>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonDetails> {
        val offset = params.key ?: 0
        val limit = 15

        return try {
            val pokemons = useCase.invoke(offset, limit)

            LoadResult.Page(
                data = pokemons,
                prevKey = if (offset == 0) null else offset - limit,
                nextKey = if (pokemons.isEmpty()) null else offset + limit
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonDetails>): Int? {
        return state.anchorPosition
    }
}
