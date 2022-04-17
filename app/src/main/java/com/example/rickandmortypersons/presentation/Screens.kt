package com.example.rickandmortypersons.presentation

import com.example.rickandmortypersons.presentation.feature.characters_list.CharactersFragment
import com.example.rickandmortypersons.presentation.feature.detail.CharacterDetailFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    val charactersFragment = FragmentScreen { CharactersFragment.newInstance() }
    fun getCharacterDetailFragment(characterID: Int) =
        FragmentScreen { CharacterDetailFragment.newInstance(characterID) }

}