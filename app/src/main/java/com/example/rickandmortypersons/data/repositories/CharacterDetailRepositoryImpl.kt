package com.example.rickandmortypersons.data.repositories

import com.example.rickandmortypersons.data.mappers.CharacterResponseRawToCharacterDetailMapper
import com.example.rickandmortypersons.data.network_utils.NetworkResult
import com.example.rickandmortypersons.data.source.RickAndMortyAPI
import com.example.rickandmortypersons.domain.entities.CharacterDetailDomain
import com.example.rickandmortypersons.domain.repositories.CharacterDetailRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.HttpException

class CharacterDetailRepositoryImpl : CharacterDetailRepository, KoinComponent {
    private val api by inject<RickAndMortyAPI>()
    private val mapper = CharacterResponseRawToCharacterDetailMapper()

    override suspend fun getCharacterById(id: Int): NetworkResult<CharacterDetailDomain> {
        try {
            val response = api.getCharacterById(id)
            if (response.isSuccessful) {
                return NetworkResult.Success(mapper.convert(response.body()!!))
            } else {
                return NetworkResult.Error(HttpException(response).message())
            }
        } catch (e: HttpException) {
            return NetworkResult.Error(e.localizedMessage)
        } catch (e: Exception) {
            return NetworkResult.Error(e.localizedMessage)
        }
    }
}