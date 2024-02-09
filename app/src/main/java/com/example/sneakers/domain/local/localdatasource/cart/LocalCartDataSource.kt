package com.example.sneakers.domain.local.localdatasource.cart

import com.example.sneakers.Sneakers
import com.example.sneakers.data.models.cart.SneakerItemDao
import kotlinx.coroutines.flow.Flow

interface LocalCartDataSource {

    suspend fun getAllCartItems(): Flow<List<SneakerItemDao>>

    suspend fun insertAllCartItems(sneakers: List<SneakerItemDao>): Flow<Unit>

    suspend fun deleteCartItem(sneakers: SneakerItemDao):Flow<Unit>
}