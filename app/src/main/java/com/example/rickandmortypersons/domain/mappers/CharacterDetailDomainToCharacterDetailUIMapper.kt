package com.example.rickandmortypersons.domain.mappers

import com.example.rickandmortypersons.domain.entities.CharacterDetailDomain
import com.example.rickandmortypersons.presentation.entities.CharacterDetailUI

class CharacterDetailDomainToCharacterDetailUIMapper {
    fun convert(characterDetailDomain: CharacterDetailDomain): CharacterDetailUI {
        return CharacterDetailUI(
            id = characterDetailDomain.id,
            name = characterDetailDomain.name,
            episodeCount = characterDetailDomain.episodeCount,
            status = characterDetailDomain.status,
            image = characterDetailDomain.image,
            location = characterDetailDomain.location,
            gender = characterDetailDomain.gender,
            race = characterDetailDomain.race
        )
    }
}