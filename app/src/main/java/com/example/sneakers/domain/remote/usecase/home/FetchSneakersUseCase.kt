package com.example.sneakers.domain.remote.usecase.home

import com.example.sneakers.network.NetworkState
import kotlinx.coroutines.flow.Flow

interface FetchSneakersUseCase {
    suspend fun fetchSneakerList(): Flow<NetworkState>
}