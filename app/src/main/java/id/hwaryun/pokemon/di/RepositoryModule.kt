package id.hwaryun.pokemon.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.hwaryun.pokemon.data.network.ApiService
import id.hwaryun.pokemon.data.repository.PokemonRepository
import id.hwaryun.pokemon.data.repository.PokemonRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): PokemonRepository {
        return PokemonRepositoryImpl(apiService)
    }
}