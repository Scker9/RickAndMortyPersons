package com.example.rickandmortypersons.domain.repositories

import com.example.rickandmortypersons.data.network_utils.NetworkResult
import com.example.rickandmortypersons.domain.entities.CharacterDetailDomain

interface CharacterDetailRepository {
    suspend fun getCharacterById(id: Int): NetworkResult<CharacterDetailDomain>
}