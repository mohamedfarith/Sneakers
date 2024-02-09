package com.example.sneakers.data.local.localdatasourceimpl.cart

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.sneakers.data.local.cart.CartService
import com.example.sneakers.data.models.cart.SneakerItemDao

@Database(entities = [SneakerItemDao::class], version = 1)
@TypeConverters(SneakerTypeConvertor::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cartService(): CartService

}