package com.example.sneakers.data.local.localusecaseimpl

import com.example.sneakers.data.models.cart.SneakerItemDao
import com.example.sneakers.domain.local.localrepository.LocalCartRepository
import com.example.sneakers.domain.local.localusecase.cart.LocalCartUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalCartUseCaseImpl @Inject constructor(private val repository: LocalCartRepository) :
    LocalCartUseCase {
    override suspend fun insertCartItems(items: List<SneakerItemDao>): Flow<Unit> {
        return repository.insertCartItems(items)
    }

    override suspend fun getAllCartItems(): Flow<List<SneakerItemDao>> {
        return repository.getAllCartItems()
    }

    override suspend fun deleteCartItem(item: SneakerItemDao): Flow<Unit> {
        return repository.deleteCartItem(item = item)
    }
}