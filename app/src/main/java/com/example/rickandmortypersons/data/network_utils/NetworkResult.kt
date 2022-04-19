package com.example.rickandmortypersons.data.network_utils

sealed class NetworkResult<T>(
    val data: T? = null,
    val error: Exception? = null
) {
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(exception: Exception, data: T? = null) : NetworkResult<T>(data, exception)

}