package com.example.rickandmortypersons.data.source

import com.example.rickandmortypersons.data.source.entities_raw.CharacterRaw
import com.example.rickandmortypersons.data.source.entities_raw.CharacterPagingRaw
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyAPI {

    @GET("character")
    suspend fun getCharactersByPage(@Query("page") page: Int): Response<CharacterPagingRaw>

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<CharacterRaw>
}