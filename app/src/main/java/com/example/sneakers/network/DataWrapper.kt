package com.example.sneakers.network

import com.example.sneakers.constants.AppConstants
import com.google.gson.Gson
import retrofit2.Response

object DataWrapper {
    inline fun <reified T> invoke(response: Response<T>): NetworkState {
        return if (response.isSuccessful) {
            NetworkState.Success(data = response.body() as T)
        } else {
            NetworkState.Failure(message = AppConstants.DEFAULT_API_FAILURE_MESSAGE)
        }
    }
}