package com.example.rickandmortypersons.domain.interactors

import com.example.rickandmortypersons.domain.repositories.CharacterDetailRepository
import com.example.rickandmortypersons.domain.validation.CharacterDetailValidationResult
import com.example.rickandmortypersons.domain.validation.CharacterDetailValidator
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterDetailInteractor : KoinComponent {
    private val characterDetailRepository: CharacterDetailRepository by inject()
    private val validator = CharacterDetailValidator()
    suspend fun getCharacterById(id: Int): CharacterDetailValidationResult {
        return validator.validate(characterDetailRepository.getCharacterById(id))
    }
}
