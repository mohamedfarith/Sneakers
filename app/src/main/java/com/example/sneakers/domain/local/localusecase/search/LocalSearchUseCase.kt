package com.example.sneakers.domain.local.localusecase.search

import com.example.sneakers.network.NetworkState
import kotlinx.coroutines.flow.Flow

interface LocalSearchUseCase {

    suspend fun getSneakersList(): Flow<NetworkState>
}