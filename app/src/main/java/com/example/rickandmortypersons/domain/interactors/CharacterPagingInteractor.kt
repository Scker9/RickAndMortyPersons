package com.example.rickandmortypersons.domain.interactors

import androidx.paging.PagingSource
import com.example.rickandmortypersons.domain.entities.CharacterListDomain
import com.example.rickandmortypersons.domain.repositories.CharactersPagingRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterPagingInteractor : KoinComponent {
    private val charactersPagingRepository: CharactersPagingRepository by inject()
    fun getCharactersPaging(): PagingSource<Int, CharacterListDomain> {
        return charactersPagingRepository.getCharactersPaging()
    }
}