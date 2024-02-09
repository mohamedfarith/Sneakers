package com.example.sneakers.data.local.localdatasourceimpl.cart

import com.example.sneakers.data.models.cart.SneakerItemDao
import com.example.sneakers.domain.local.localdatasource.cart.LocalCartDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalCartDataSourceImpl @Inject constructor(private val appDatabase: AppDatabase) :
    LocalCartDataSource {
    override suspend fun getAllCartItems(): Flow<List<SneakerItemDao>> {
        return flow {
            emit(appDatabase.cartService().getAll())
        }
    }

    override suspend fun insertAllCartItems(sneakers: List<SneakerItemDao>): Flow<Unit> {
        return flow {
            emit(appDatabase.cartService().insertAll(sneakers))
        }
    }

    override suspend fun deleteCartItem(sneakers: SneakerItemDao): Flow<Unit> {
        return flow {
            emit(appDatabase.cartService().delete(sneakers))
        }
    }
}