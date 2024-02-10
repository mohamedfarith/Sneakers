package com.example.sneakers.data.local.localrepositoryimpl.search

import com.example.sneakers.domain.local.localdatasource.search.LocalSearchDataSource
import com.example.sneakers.domain.local.localrepository.search.LocalSearchRepository
import com.example.sneakers.network.NetworkState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalSearchRepositoryImpl @Inject constructor(private val datasource: LocalSearchDataSource) :
    LocalSearchRepository {
    override suspend fun getSneakersList(): Flow<NetworkState> {
        return datasource.getSneakersList()
    }
}