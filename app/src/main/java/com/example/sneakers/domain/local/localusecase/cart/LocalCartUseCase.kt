package com.example.sneakers.domain.local.localusecase.cart

import com.example.sneakers.data.models.cart.SneakerItemDao
import kotlinx.coroutines.flow.Flow

interface LocalCartUseCase {
    suspend fun insertCartItems(items:List<SneakerItemDao>): Flow<Unit>

    suspend fun getAllCartItems(): Flow<List<SneakerItemDao>>

    suspend fun deleteCartItem(item:SneakerItemDao): Flow<Unit>


}