package com.example.sneakers.data.remote.datasourceimpl.search

import com.example.sneakers.constants.AppConstants
import com.example.sneakers.data.models.home.SneakerListDto
import com.example.sneakers.domain.remote.datasource.search.SearchDataSource
import com.example.sneakers.network.NetworkState
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class SearchDataSourceImpl : SearchDataSource {
    override suspend fun searchItems(matcher: String): Flow<NetworkState> {
        return flow<NetworkState> {
            emit(NetworkState.Success(Gson().fromJson(fakeSearchData(), SneakerListDto::class.java)))
        }.catch {
            emit(NetworkState.Failure(message = AppConstants.DEFAULT_API_FAILURE_MESSAGE))
        }
    }
}

private fun fakeSearchData(): String {
    return ""
}