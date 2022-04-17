package com.example.rickandmortypersons.domain.repositories

import androidx.paging.PagingSource
import com.example.rickandmortypersons.data.entities.Character
import com.example.rickandmortypersons.data.network_utils.NetworkResult

interface RickAndMortyRepository {
    fun getCharactersPaging(): PagingSource<Int, Character>
    suspend fun getCharacterById(id: Int): NetworkResult<Character>
}