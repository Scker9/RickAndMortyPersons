package com.example.rickandmortypersons.domain.interactors

import com.example.rickandmortypersons.data.network_utils.NetworkResult
import com.example.rickandmortypersons.domain.mappers.CharacterDetailDomainToCharacterDetailUIMapper
import com.example.rickandmortypersons.domain.repositories.CharacterDetailRepository
import com.example.rickandmortypersons.presentation.entities.CharacterDetailUI
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterDetailInteractor : KoinComponent {
    private val characterDetailRepository: CharacterDetailRepository by inject()
    private val mapper = CharacterDetailDomainToCharacterDetailUIMapper()
    suspend fun getCharacterById(id: Int): NetworkResult<CharacterDetailUI> {
        return characterDetailRepository.getCharacterById(id).map {
            mapper.convert(it)
        }
    }
}
