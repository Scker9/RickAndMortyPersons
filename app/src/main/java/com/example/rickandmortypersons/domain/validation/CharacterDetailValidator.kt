package com.example.rickandmortypersons.domain.validation

import com.example.rickandmortypersons.data.network_utils.NetworkResult
import com.example.rickandmortypersons.domain.entities.CharacterDetailDomain
import retrofit2.HttpException

class CharacterDetailValidator {
    fun validate(characterDetailNetworkResult: NetworkResult<CharacterDetailDomain>): CharacterDetailValidationResult {

        when (characterDetailNetworkResult) {
            is NetworkResult.Success -> {
                return CharacterDetailValidationResult.Success(characterDetailNetworkResult.data!!)
            }
            is NetworkResult.Error -> {
                if (characterDetailNetworkResult.error is HttpException) {
                    return CharacterDetailValidationResult.NetworkError(characterDetailNetworkResult.error)
                }
                if (characterDetailNetworkResult.error is Exception) {
                    return CharacterDetailValidationResult.OtherError(characterDetailNetworkResult.error)
                } else {
                    return CharacterDetailValidationResult.OtherError(Exception("Unknown Error"))
                }
            }
        }
    }
}