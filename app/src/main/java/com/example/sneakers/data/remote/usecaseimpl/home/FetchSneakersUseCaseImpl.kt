package com.example.sneakers.data.usecaseimpl.home

import com.example.sneakers.domain.repository.home.HomeRepository
import com.example.sneakers.domain.remote.usecase.home.FetchSneakersUseCase
import com.example.sneakers.network.NetworkState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchSneakersUseCaseImpl @Inject constructor(private val repository: HomeRepository) :
    FetchSneakersUseCase {
    override suspend fun fetchSneakerList(): Flow<NetworkState> {
        return repository.getSneakersList()
    }
}