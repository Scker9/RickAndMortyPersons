package com.example.rickandmortypersons.data.mappers

import com.example.rickandmortypersons.data.entities.Character
import com.example.rickandmortypersons.data.source.entities_raw.CharacterResponseRaw

class CharacterResponseRawToCharacterMapper {
    fun convert(characterResponseRaw: CharacterResponseRaw): List<Character> {
        return characterResponseRaw.results.map {
            Character(
                id = it.id,
                name = it.name,
                image = it.image,
                gender = it.gender,
                episode = it.episode,
                race = it.species
            )
        }
    }
}