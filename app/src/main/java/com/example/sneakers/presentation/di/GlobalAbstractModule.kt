package com.example.sneakers.presentation.di

import com.example.sneakers.presentation.DefaultDispatcherProvider
import com.example.sneakers.presentation.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class GlobalAbstractModule {
    @Binds
    abstract fun dispatcherProvider(defaultProvider: DefaultDispatcherProvider): DispatcherProvider
}