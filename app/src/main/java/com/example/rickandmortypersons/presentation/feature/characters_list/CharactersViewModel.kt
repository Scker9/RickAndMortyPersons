package com.example.rickandmortypersons.presentation.feature.characters_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickandmortypersons.data.network_utils.NetworkResult
import com.example.rickandmortypersons.domain.interactors.CharacterInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharactersViewModel : ViewModel(), KoinComponent {
    private val characterInteractor by inject<CharacterInteractor>()

    val pager = Pager(PagingConfig(2)) {
        characterInteractor.getCharactersPaging()
    }.flow.cachedIn(viewModelScope)


}