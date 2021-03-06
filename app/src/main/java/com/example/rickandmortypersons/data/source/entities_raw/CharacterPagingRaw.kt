package com.example.rickandmortypersons.data.source.entities_raw

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterPagingRaw(
    @SerialName("info")  val info: Info,
    @SerialName("results") val characterRaws: List<CharacterRaw>
)
