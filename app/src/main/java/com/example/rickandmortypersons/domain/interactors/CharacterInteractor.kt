package com.example.rickandmortypersons.domain.interactors

import androidx.paging.PagingSource
import com.example.rickandmortypersons.data.entities.Character
import com.example.rickandmortypersons.domain.repositories.RickAndMortyRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterInteractor : KoinComponent {
    private val rickAndMortyRepository: RickAndMortyRepository by inject()

    fun getCharacters(): PagingSource<Int, Character> {
        return rickAndMortyRepository.getCharacters()
    }
}