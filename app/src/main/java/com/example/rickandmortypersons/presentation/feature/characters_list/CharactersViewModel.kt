package com.example.rickandmortypersons.presentation.feature.characters_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.rickandmortypersons.domain.interactors.CharacterPagingInteractor
import com.example.rickandmortypersons.domain.mappers.CharacterListDomainToCharacterListUIMapper
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharactersViewModel : ViewModel(), KoinComponent {
    private val characterInteractor by inject<CharacterPagingInteractor>()
    private val mapper = CharacterListDomainToCharacterListUIMapper()
    val pager = Pager(PagingConfig(2)) {
        characterInteractor.getCharactersPaging()
    }.flow.map { pagingData ->
        pagingData.map {
            mapper.convert(it)
        }
    }.cachedIn(viewModelScope)


}
