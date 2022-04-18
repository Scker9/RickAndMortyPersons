package com.example.rickandmortypersons.data.network_utils

sealed class NetworkResult<T>(
    val data: T? = null,
    val errorMessage: String? = null
) {
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(errorMessage: String, data: T? = null) : NetworkResult<T>(data, errorMessage)

    inline fun <R> map(transform: ((T) -> R)): NetworkResult<R> {
        return when (this) {
            is Success -> {
                Success(transform(this.data!!))
            }
            is Error -> {
                Error(this.errorMessage ?: UNKNOWN_ERROR)
            }
        }
    }

    companion object {
        const val UNKNOWN_ERROR = "Unknown Error"
    }
}