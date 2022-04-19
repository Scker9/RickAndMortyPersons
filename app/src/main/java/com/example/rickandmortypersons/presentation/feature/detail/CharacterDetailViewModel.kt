package com.example.rickandmortypersons.presentation.feature.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortypersons.domain.interactors.CharacterDetailInteractor
import com.example.rickandmortypersons.domain.mappers.CharacterDetailDomainToCharacterDetailUIMapper
import com.example.rickandmortypersons.domain.validation.CharacterDetailValidationResult
import com.example.rickandmortypersons.presentation.entities.CharacterDetailUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterDetailViewModel : ViewModel(), KoinComponent {
    private val characterInteractor by inject<CharacterDetailInteractor>()
    private var errorMessage: String? = null
    val characterLiveData = MutableLiveData<CharacterDetailUI>()
    val state = MutableLiveData(CharacterDetailState.LOADING)
    private val mapper = CharacterDetailDomainToCharacterDetailUIMapper()

    fun fetchData(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = characterInteractor.getCharacterById(id)) {
                is CharacterDetailValidationResult.Success -> {
                    characterLiveData.postValue(mapper.convert(result.data!!))
                    makeShowState()
                }
                is CharacterDetailValidationResult.NetworkError -> {
                    errorMessage = result.message
                    makeErrorState()
                }
                is CharacterDetailValidationResult.OtherError -> {
                    errorMessage = result.message
                    makeErrorState()
                }
            }
        }
    }

    private fun makeShowState() {
        state.postValue(CharacterDetailState.SHOW)
    }

    private fun makeErrorState() {
        state.postValue(CharacterDetailState.ERROR)
    }

    fun getErrorMsg(): String {
        return errorMessage ?: "Unknown Error"
    }
}