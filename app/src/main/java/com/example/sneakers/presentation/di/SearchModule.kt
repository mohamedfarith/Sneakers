package com.example.sneakers.presentation.di

import com.example.sneakers.data.local.localdatasourceimpl.search.LocalSearchDataSourceImpl
import com.example.sneakers.data.local.localrepositoryimpl.search.LocalSearchRepositoryImpl
import com.example.sneakers.data.local.localusecaseimpl.search.LocalSearchUseCaseImpl
import com.example.sneakers.domain.local.localdatasource.search.LocalSearchDataSource
import com.example.sneakers.domain.local.localrepository.search.LocalSearchRepository
import com.example.sneakers.domain.local.localusecase.search.LocalSearchUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SearchModule {

    @Binds
    abstract fun getLocalSearchUseCase(useCase: LocalSearchUseCaseImpl): LocalSearchUseCase

    @Binds
    abstract fun getLocalSearchRepository(repositoryImpl: LocalSearchRepositoryImpl): LocalSearchRepository

    @Binds
    abstract fun getLocalSearchDataSource(dataSource: LocalSearchDataSourceImpl): LocalSearchDataSource
}