package com.example.rickandmortypersons.presentation.entities

data class CharacterDetailUI(
    val id: Long,
    val name: String,
    val gender: String,
    val race: String,
    val status: String,
    val image: String,
    val location: String,
    val episodeCount: String
)
