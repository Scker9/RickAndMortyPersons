package com.example.rickandmortypersons.data.network_utils

sealed class NetworkResult<T>(
    val data: T? = null,
    val errorMessage: String? = null
) {
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(errorMessage: String, data: T? = null) : NetworkResult<T>(data, errorMessage)
}