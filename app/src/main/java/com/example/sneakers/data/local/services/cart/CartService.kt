package com.example.sneakers.data.local.cart

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sneakers.data.models.cart.SneakerItemDao

@Dao
interface CartService {

    @Query("SELECT * FROM SneakerItemDao")
    fun getAll(): List<SneakerItemDao>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<SneakerItemDao>)

    @Delete
    fun delete(user: SneakerItemDao)

}