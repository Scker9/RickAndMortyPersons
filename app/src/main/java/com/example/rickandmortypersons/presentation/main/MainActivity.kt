package com.example.rickandmortypersons.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rickandmortypersons.R
import com.example.rickandmortypersons.presentation.Screens
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent

class MainActivity : AppCompatActivity(), KoinComponent {
    private val navigatorHolder by inject<NavigatorHolder>()
    private val router by inject<Router>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToCharactersFragment()
    }

    private fun navigateToCharactersFragment() {
        val navigator = AppNavigator(this, R.id.main_container)
        navigatorHolder.setNavigator(navigator)
        router.newRootScreen(Screens.charactersFragment)
    }
}