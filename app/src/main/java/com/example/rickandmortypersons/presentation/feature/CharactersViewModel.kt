package com.example.rickandmortypersons.presentation.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmortypersons.data.entities.Character
import com.example.rickandmortypersons.domain.interactors.CharacterInteractor
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharactersViewModel : ViewModel(), KoinComponent {
    private val items = ArrayList<Character>()
    private val characterInteractor by inject<CharacterInteractor>()
    val pager = Pager(PagingConfig(1)) {
        characterInteractor.getCharacters()
    }.flow.cachedIn(viewModelScope)

    fun fetchData() {

    }

}
