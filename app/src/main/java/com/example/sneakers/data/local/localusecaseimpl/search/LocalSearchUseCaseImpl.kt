package com.example.sneakers.data.local.localusecaseimpl.search

import com.example.sneakers.domain.local.localrepository.search.LocalSearchRepository
import com.example.sneakers.domain.local.localusecase.search.LocalSearchUseCase
import com.example.sneakers.network.NetworkState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalSearchUseCaseImpl @Inject constructor(private val repository: LocalSearchRepository) :
    LocalSearchUseCase {
    override suspend fun getSneakersList(): Flow<NetworkState> {
       return repository.getSneakersList()
    }
}