package com.example.rickandmortypersons.data.mappers

import com.example.rickandmortypersons.data.entities.Character
import com.example.rickandmortypersons.data.source.entities_raw.CharacterRaw
import com.example.rickandmortypersons.data.source.entities_raw.CharacterResponseRaw

class CharacterResponseRawToCharacterMapper {
    fun convert(characterResponseRaw: CharacterResponseRaw): List<Character> {
        return characterResponseRaw.characterRaws.map {
            Character(
                id = it.id,
                name = it.name,
                image = it.image,
                gender = it.gender,
                episode = it.episode,
                race = it.species,
                location = it.location.name,
                status = it.status
            )
        }
    }

    fun convert(characterRaw: CharacterRaw): Character {
        return Character(
            id = characterRaw.id,
            name = characterRaw.name,
            image = characterRaw.image,
            gender = characterRaw.gender,
            episode = characterRaw.episode,
            location = characterRaw.location.name,
            race = characterRaw.species,
            status = characterRaw.status
        )
    }
}