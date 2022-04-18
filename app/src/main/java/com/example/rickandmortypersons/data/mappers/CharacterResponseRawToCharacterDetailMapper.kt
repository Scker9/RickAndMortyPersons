package com.example.rickandmortypersons.data.mappers

import com.example.rickandmortypersons.data.source.entities_raw.CharacterRaw
import com.example.rickandmortypersons.domain.entities.CharacterDetailDomain

class CharacterResponseRawToCharacterDetailMapper {
    fun convert(characterRaw: CharacterRaw): CharacterDetailDomain {
        return CharacterDetailDomain(
            id = characterRaw.id,
            name = characterRaw.name,
            image = characterRaw.image,
            gender = characterRaw.gender,
            episodeCount = characterRaw.episode.count().toString(),
            location = characterRaw.location.name,
            race = characterRaw.species,
            status = characterRaw.status
        )
    }
}