package com.example.sneakers.domain.repository.search

import com.example.sneakers.network.NetworkState
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun searchItems(matcher: String): Flow<NetworkState>
}