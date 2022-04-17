package com.example.rickandmortypersons.data.source.entities_raw

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Info(
    @SerialName("count") val count: Long,
    @SerialName("pages") val pages: Long,
    @SerialName("next") val next: String?,
    @SerialName("prev") val prev: String?
)