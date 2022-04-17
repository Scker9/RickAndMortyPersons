package com.example.rickandmortypersons.data.repositories

import androidx.paging.PagingSource
import com.example.rickandmortypersons.data.entities.Character
import com.example.rickandmortypersons.data.source.paging.CharactersPagingDataSource
import com.example.rickandmortypersons.domain.repositories.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response

class RickAndMortyRepositoryImpl : RickAndMortyRepository, KoinComponent {
    private val pagingSource: CharactersPagingDataSource by inject()

    override fun getCharacters(): PagingSource<Int, Character> {
        return pagingSource
    }

    override fun getCharacterById(): Flow<Response<Character>> {
        TODO("Not yet implemented")
    }
}