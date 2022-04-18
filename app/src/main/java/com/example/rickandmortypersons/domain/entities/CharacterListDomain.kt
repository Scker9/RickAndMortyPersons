package com.example.rickandmortypersons.domain.entities

data class CharacterListDomain(
    val id: Long,
    val name: String,
    val gender: String,
    val race: String,
    val image: String
)
