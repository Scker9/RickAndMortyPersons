package com.example.rickandmortypersons.domain.repositories

import androidx.paging.PagingSource
import com.example.rickandmortypersons.domain.entities.CharacterListDomain

interface CharactersPagingRepository {
    fun getCharactersPaging(): PagingSource<Int, CharacterListDomain>
}