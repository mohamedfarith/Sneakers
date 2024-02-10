package com.example.sneakers.presentation.di

import com.example.sneakers.data.local.localdatasourceimpl.cart.LocalCartDataSourceImpl
import com.example.sneakers.data.local.localrepositoryimpl.cart.LocalCartRepositoryImpl
import com.example.sneakers.data.local.localusecaseimpl.LocalCartUseCaseImpl
import com.example.sneakers.domain.local.localdatasource.cart.LocalCartDataSource
import com.example.sneakers.domain.local.localrepository.LocalCartRepository
import com.example.sneakers.domain.local.localusecase.cart.LocalCartUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CartModule {

    @Binds
    abstract fun getLocalCartUseCase(useCase: LocalCartUseCaseImpl): LocalCartUseCase

    @Binds
    abstract fun getLocalRepository(repositoryImpl: LocalCartRepositoryImpl): LocalCartRepository

    @Binds
    abstract fun getLocalDataSource(dataSource: LocalCartDataSourceImpl): LocalCartDataSource
}