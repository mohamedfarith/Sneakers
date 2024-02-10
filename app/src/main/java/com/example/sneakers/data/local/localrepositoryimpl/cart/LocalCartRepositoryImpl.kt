package com.example.sneakers.data.local.localrepositoryimpl.cart

import com.example.sneakers.data.models.cart.SneakerItemDao
import com.example.sneakers.domain.local.localdatasource.cart.LocalCartDataSource
import com.example.sneakers.domain.local.localrepository.LocalCartRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalCartRepositoryImpl @Inject constructor(private val dataSource: LocalCartDataSource):LocalCartRepository {
    override suspend fun insertCartItems(items: List<SneakerItemDao>): Flow<Unit> {
        return dataSource.insertAllCartItems(items)
    }

    override suspend fun getAllCartItems(): Flow<List<SneakerItemDao>> {
        return dataSource.getAllCartItems()
    }

    override suspend fun deleteCartItem(item: SneakerItemDao): Flow<Unit> {
        return dataSource.deleteCartItem(item)
    }
}