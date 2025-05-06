package com.scafisystems.pokemoncards.data.remote.api

import com.scafisystems.pokemoncards.data.remote.entity.PokemonDetailsDao
import com.scafisystems.pokemoncards.data.remote.entity.PokemonListDao
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemotePokemonService {

    @GET
    suspend fun getListPokemons(): PokemonListDao

    @GET(".")
    suspend fun getListPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonListDao

    @GET("{id}")
    suspend fun getPokemonById(@Path("id") id: String): PokemonDetailsDao

    @GET("{name}")
    suspend fun getPokemonByName(@Path("name") name: String): PokemonDetailsDao
}