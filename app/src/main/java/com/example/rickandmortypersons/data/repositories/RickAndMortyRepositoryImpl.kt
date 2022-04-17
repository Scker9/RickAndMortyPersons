package com.example.rickandmortypersons.data.repositories

import android.util.Log
import androidx.paging.PagingSource
import com.example.rickandmortypersons.data.entities.Character
import com.example.rickandmortypersons.data.mappers.CharacterResponseRawToCharacterMapper
import com.example.rickandmortypersons.data.network_utils.NetworkResult
import com.example.rickandmortypersons.data.source.RickAndMortyAPI
import com.example.rickandmortypersons.data.source.paging.CharactersPagingDataSource
import com.example.rickandmortypersons.domain.repositories.RickAndMortyRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.HttpException

class RickAndMortyRepositoryImpl : RickAndMortyRepository, KoinComponent {
    private val pagingSource: CharactersPagingDataSource by inject()
    private val api by inject<RickAndMortyAPI>()

    override fun getCharactersPaging(): PagingSource<Int, Character> {
        return pagingSource
    }

    override suspend fun getCharacterById(id: Int): NetworkResult<Character> {
        val mapper = CharacterResponseRawToCharacterMapper()
        val response = api.getCharacterById(id)
        if (response.isSuccessful) {
            return NetworkResult.Success(mapper.convert(response.body()!!))
        } else {
            return NetworkResult.Error(HttpException(response).message())
        }
    }
}
