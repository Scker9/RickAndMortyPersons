package com.example.rickandmortypersons.presentation

import com.example.rickandmortypersons.presentation.feature.CharactersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    val characterFragment = FragmentScreen {
        CharactersFragment.newInstance()
    }
}