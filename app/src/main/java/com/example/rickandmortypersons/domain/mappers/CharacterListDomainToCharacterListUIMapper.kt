package com.example.rickandmortypersons.domain.mappers

import com.example.rickandmortypersons.domain.entities.CharacterListDomain
import com.example.rickandmortypersons.presentation.entities.CharacterListUI

class CharacterListDomainToCharacterListUIMapper {
    fun convert(characterListDomain: CharacterListDomain): CharacterListUI {
        return CharacterListUI(
            id = characterListDomain.id,
            name = characterListDomain.name,
            image = characterListDomain.image,
            gender = characterListDomain.gender,
            race = characterListDomain.race
        )
    }
}