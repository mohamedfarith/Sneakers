package com.example.sneakers.data.repositoryimpl.home

import com.example.sneakers.domain.remote.datasource.home.HomeDataSource
import com.example.sneakers.domain.repository.home.HomeRepository
import com.example.sneakers.network.NetworkState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val datasource: HomeDataSource) : HomeRepository {
    override suspend fun getSneakersList(): Flow<NetworkState> {
       return datasource.getTopSneakersList()
    }
}