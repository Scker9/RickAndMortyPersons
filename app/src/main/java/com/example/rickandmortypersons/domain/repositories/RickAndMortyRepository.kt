package com.example.rickandmortypersons.domain.repositories

import androidx.paging.PagingSource
import com.example.rickandmortypersons.data.entities.Character
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RickAndMortyRepository {
    fun getCharacters(): PagingSource<Int, Character>
    fun getCharacterById(): Flow<Response<Character>>
}