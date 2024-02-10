package com.example.sneakers.domain.local.localrepository.search

import com.example.sneakers.network.NetworkState
import kotlinx.coroutines.flow.Flow

interface LocalSearchRepository {
    suspend fun getSneakersList(): Flow<NetworkState>
}