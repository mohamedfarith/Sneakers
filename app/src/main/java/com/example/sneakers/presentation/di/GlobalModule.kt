package com.example.sneakers.presentation.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sneakers.data.local.localdatasourceimpl.cart.AppDatabase
import com.example.sneakers.presentation.DefaultDispatcherProvider
import com.example.sneakers.presentation.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object GlobalModule {

    @Provides
    fun getRoomDbInstance(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "sneker-db"
        ).build()
    }
}