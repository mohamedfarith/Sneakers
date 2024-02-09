package com.example.sneakers.domain.usecase.search

import com.example.sneakers.network.NetworkState
import kotlinx.coroutines.flow.Flow

interface SearchItemUseCase {

    suspend fun searchItems(matcher: String): Flow<NetworkState>
}