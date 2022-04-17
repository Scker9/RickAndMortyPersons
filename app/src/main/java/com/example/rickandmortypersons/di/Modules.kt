package com.example.rickandmortypersons.di

import com.example.rickandmortypersons.data.repositories.RickAndMortyRepositoryImpl
import com.example.rickandmortypersons.data.source.RickAndMortyAPI
import com.example.rickandmortypersons.data.source.paging.CharactersPagingDataSource
import com.example.rickandmortypersons.domain.interactors.CharacterInteractor
import com.example.rickandmortypersons.domain.repositories.RickAndMortyRepository
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

object Modules {
    private const val rickAndMortyURL = "https://rickandmortyapi.com/api/"
    val network = module {
        single {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }
        single {
            Json(Json) {
                ignoreUnknownKeys = true
            }
        }
        single<RickAndMortyAPI> {
            val json: Json = get()
            Retrofit.Builder()
                .baseUrl(rickAndMortyURL)
                .client(get())
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()
                .create(RickAndMortyAPI::class.java)
        }
    }
    val router = module {
        single { Cicerone.create() }
        single { get<Cicerone<Router>>().router }
        single { get<Cicerone<Router>>().getNavigatorHolder() }
    }

    val repositories = module {
        factory<RickAndMortyRepository> { RickAndMortyRepositoryImpl() }
    }

    val interactors = module {
        factory {
            CharacterInteractor()
        }
    }
    val paging = module {
        factory {
            CharactersPagingDataSource()
        }
    }


}