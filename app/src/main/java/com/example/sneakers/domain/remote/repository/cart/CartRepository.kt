package com.example.sneakers.domain.repository.cart

import com.example.sneakers.data.models.cart.SneakerItemDao
import com.example.sneakers.network.NetworkState
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun getCartItems():Flow<List<SneakerItemDao>>
    suspend fun deleteCartItem():Flow<Unit>
    suspend fun insertItems(items:List<SneakerItemDao>)
}