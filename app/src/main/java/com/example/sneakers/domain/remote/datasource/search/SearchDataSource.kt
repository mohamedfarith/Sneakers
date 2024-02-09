package com.example.sneakers.domain.remote.datasource.search

import com.example.sneakers.network.NetworkState
import kotlinx.coroutines.flow.Flow

interface SearchDataSource {
    suspend fun searchItems(matcher:String):Flow<NetworkState>
}