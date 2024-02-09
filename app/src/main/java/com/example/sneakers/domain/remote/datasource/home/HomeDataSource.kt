package com.example.sneakers.domain.remote.datasource.home

import com.example.sneakers.network.NetworkState
import kotlinx.coroutines.flow.Flow

interface HomeDataSource {
    suspend fun getTopSneakersList(): Flow<NetworkState>
}