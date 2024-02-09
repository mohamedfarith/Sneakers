package com.example.sneakers.domain.repository.home

import com.example.sneakers.network.NetworkState
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getSneakersList(): Flow<NetworkState>
}