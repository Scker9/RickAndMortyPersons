package com.example.rickandmortypersons.data.entities

data class Character(
    val id: Long,
    val name: String,
    val gender: String,
    val race: String,
    val image: String,
    val episode: List<String>,
)
