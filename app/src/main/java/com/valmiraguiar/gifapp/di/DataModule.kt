package com.valmiraguiar.gifapp.di

import com.valmiraguiar.core.domain.repository.GifRepository
import com.valmiraguiar.core.domain.usecase.GetTrendingGifsUseCase
import com.valmiraguiar.gifapp.data.datasource.GifRemoteDataSource
import com.valmiraguiar.gifapp.data.datasource.GifRemoteDataSourceImpl
import com.valmiraguiar.gifapp.data.repository.GifRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindGifRepository(
        repositoryImpl: GifRepositoryImpl
    ): GifRepository

    @Binds
    @Singleton
    abstract fun bindGifRemoteDataSource(
        dataSourceImpl: GifRemoteDataSourceImpl
    ): GifRemoteDataSource

    companion object {
        @Provides
        @Singleton
        fun provideGetTrendingGifsUseCase(
            repository: GifRepository
        ): GetTrendingGifsUseCase = GetTrendingGifsUseCase(repository)
    }
}
