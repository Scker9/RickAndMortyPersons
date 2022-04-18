package com.example.rickandmortypersons

import android.app.Application
import com.example.rickandmortypersons.di.Modules.interactors
import com.example.rickandmortypersons.di.Modules.network
import com.example.rickandmortypersons.di.Modules.repositories
import com.example.rickandmortypersons.di.Modules.router
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(network, router, repositories, interactors))
        }
    }
}