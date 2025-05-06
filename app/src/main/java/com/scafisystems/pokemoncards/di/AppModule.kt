package com.scafisystems.pokemoncards.di

import android.content.Context
import androidx.room.Room
import com.scafisystems.pokemoncards.data.local.dao.PokemonDao
import com.scafisystems.pokemoncards.data.local.db.PokemonDatabase
import com.scafisystems.pokemoncards.data.remote.paging.PokemonPagingSource
import com.scafisystems.pokemoncards.data.repository.LocalPokemonRepository
import com.scafisystems.pokemoncards.data.repository.RemotePokemonRepository
import com.scafisystems.pokemoncards.domain.datasource.LocalPokemonDataSource
import com.scafisystems.pokemoncards.domain.datasource.RemotePokemonDataSource
import com.scafisystems.pokemoncards.domain.usecase.GetPagedPokemonListUseCase
import com.scafisystems.pokemoncards.domain.usecase.GetPokemonByNameLocalUseCase
import com.scafisystems.pokemoncards.domain.usecase.GetPokemonFavoriteListUseCase
import com.scafisystems.pokemoncards.domain.usecase.GetPokemonListUseCase
import com.scafisystems.pokemoncards.domain.usecase.GetSelectPokemonByIdUseCase
import com.scafisystems.pokemoncards.domain.usecase.GetSelectPokemonByNameUseCase
import com.scafisystems.pokemoncards.domain.usecase.RemovePokemonFavoriteUseCase
import com.scafisystems.pokemoncards.domain.usecase.SetPokemonFavoriteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // --- Repositories ---
    @Provides
    @Singleton
    fun provideLocalPokemonRepository(dao: PokemonDao): LocalPokemonDataSource {
        return LocalPokemonRepository(dao)
    }

    @Provides
    @Singleton
    fun provideRemotePokemonRepository(): RemotePokemonDataSource {
        return RemotePokemonRepository()
    }

    @Provides
    @Singleton
    fun providesPokemonPagingSource(useCase: GetPagedPokemonListUseCase): PokemonPagingSource {
        return PokemonPagingSource(useCase)
    }

    // --- Local DB ---
    @Provides
    @Singleton
    fun providePokemonDatabase(@ApplicationContext context: Context): PokemonDatabase {
        return Room.databaseBuilder(
            context,
            PokemonDatabase::class.java,
            "pokemon_db"
        ).build()
    }

    @Provides
    fun providePokemonDao(database: PokemonDatabase): PokemonDao {
        return database.pokemonDao()
    }

    // --- UseCases ---
    @Provides
    @Singleton
    fun providesGetPokemonListUseCase(dataSource: RemotePokemonDataSource): GetPokemonListUseCase {
        return GetPokemonListUseCase(dataSource)
    }

    @Provides
    @Singleton
    fun providesGetSelectPokemonByIdUseCase(dataSource: RemotePokemonDataSource): GetSelectPokemonByIdUseCase {
        return GetSelectPokemonByIdUseCase(dataSource)
    }

    @Provides
    @Singleton
    fun providesGetSelectPokemonByNameUseCase(dataSource: RemotePokemonDataSource): GetSelectPokemonByNameUseCase {
        return GetSelectPokemonByNameUseCase(dataSource)
    }

    @Provides
    @Singleton
    fun providesGetPokemonByNameLocalUseCase(dataSource: LocalPokemonDataSource): GetPokemonByNameLocalUseCase {
        return GetPokemonByNameLocalUseCase(dataSource)
    }

    @Provides
    @Singleton
    fun providesGetPokemonFavoriteListUseCase(dataSource: LocalPokemonDataSource): GetPokemonFavoriteListUseCase {
        return GetPokemonFavoriteListUseCase(dataSource)
    }

    @Provides
    @Singleton
    fun providesRemovePokemonFavoriteUseCase(dataSource: LocalPokemonDataSource): RemovePokemonFavoriteUseCase {
        return RemovePokemonFavoriteUseCase(dataSource)
    }


    @Provides
    @Singleton
    fun providesSetPokemonFavoriteUseCase(dataSource: LocalPokemonDataSource): SetPokemonFavoriteUseCase {
        return SetPokemonFavoriteUseCase(dataSource)
    }

    @Provides
    @Singleton
    fun providesGetPagedPokemonListUseCase(dataSource: RemotePokemonDataSource): GetPagedPokemonListUseCase {
        return GetPagedPokemonListUseCase(dataSource)
    }
}

