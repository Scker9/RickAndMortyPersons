package com.example.rickandmortypersons.domain.validation

import com.example.rickandmortypersons.domain.entities.CharacterDetailDomain
import retrofit2.HttpException

sealed class CharacterDetailValidationResult(
    val data: CharacterDetailDomain? = null,
    val message: String? = null
) {
    class Success(data: CharacterDetailDomain) :
        CharacterDetailValidationResult(data)

    class NetworkError(exception: HttpException) :
        CharacterDetailValidationResult(null, exception.localizedMessage)

    class OtherError(exception: Exception) :
        CharacterDetailValidationResult(null, exception.localizedMessage)
}

