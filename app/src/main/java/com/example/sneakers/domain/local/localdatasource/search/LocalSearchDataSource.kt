package com.example.sneakers.domain.local.localdatasource.search

import com.example.sneakers.network.NetworkState
import kotlinx.coroutines.flow.Flow

interface LocalSearchDataSource {
    suspend fun getSneakersList(): Flow<NetworkState>
}