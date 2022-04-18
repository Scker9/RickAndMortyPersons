package com.example.rickandmortypersons.data.repositories

import androidx.paging.PagingSource
import com.example.rickandmortypersons.data.source.RickAndMortyAPI
import com.example.rickandmortypersons.data.source.paging.CharactersPagingDataSource
import com.example.rickandmortypersons.domain.entities.CharacterListDomain
import com.example.rickandmortypersons.domain.repositories.CharactersPagingRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharactersPagingRepositoryImpl : CharactersPagingRepository, KoinComponent {

    private val api by inject<RickAndMortyAPI>()
    private val pagingSource = CharactersPagingDataSource(api)

    override fun getCharactersPaging(): PagingSource<Int, CharacterListDomain> {
        return pagingSource
    }


}
