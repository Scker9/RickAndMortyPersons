package com.example.rickandmortypersons.data.source.entities_raw

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
)
