package com.example.rickandmortypersons.data.mappers

import com.example.rickandmortypersons.data.source.entities_raw.CharacterPagingRaw
import com.example.rickandmortypersons.domain.entities.CharacterListDomain

class CharacterResponseRawToCharacterListDomainMapper {
    fun convert(characterPagingRaw: CharacterPagingRaw): List<CharacterListDomain> {
        return characterPagingRaw.characterRaws.map {
            CharacterListDomain(
                id = it.id,
                name = it.name,
                image = it.image,
                gender = it.gender,
                race = it.species
            )
        }
    }
}