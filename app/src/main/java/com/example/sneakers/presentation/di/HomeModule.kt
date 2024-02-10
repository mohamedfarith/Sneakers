package com.example.sneakers.presentation.di

import com.example.sneakers.data.local.localusecaseimpl.LocalCartUseCaseImpl
import com.example.sneakers.data.remote.datasourceimpl.home.HomeDataSourceImpl
import com.example.sneakers.data.repositoryimpl.home.HomeRepositoryImpl
import com.example.sneakers.data.usecaseimpl.home.FetchSneakersUseCaseImpl
import com.example.sneakers.domain.local.localusecase.cart.LocalCartUseCase
import com.example.sneakers.domain.remote.datasource.home.HomeDataSource
import com.example.sneakers.domain.repository.home.HomeRepository
import com.example.sneakers.domain.remote.usecase.home.FetchSneakersUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeModule {

    @Binds
    abstract fun provideUseCaseReference(useCase: FetchSneakersUseCaseImpl): FetchSneakersUseCase

    @Binds
    abstract fun provideRepoReference(repository: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun provideHomeDataSourceReference(dataSource: HomeDataSourceImpl): HomeDataSource

}