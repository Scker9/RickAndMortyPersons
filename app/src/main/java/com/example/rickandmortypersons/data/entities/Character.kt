package com.example.rickandmortypersons.data.entities

data class Character(
    val id: Long,
    val name: String,
    val gender: String,
    val race: String,
    val status: String,
    val image: String,
    val location: String,
    val episode: List<String>,
) {
    val episodeCount get() = this.episode.count().toString()
}
