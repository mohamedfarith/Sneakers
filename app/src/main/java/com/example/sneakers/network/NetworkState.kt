package com.example.sneakers.network

sealed class NetworkState {
    data class Success(val data: Any?) : NetworkState()
    data class Failure(val message: String) : NetworkState()
    data object Loading: NetworkState()
}