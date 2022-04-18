package com.example.rickandmortypersons.presentation.feature.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortypersons.data.network_utils.NetworkResult
import com.example.rickandmortypersons.domain.interactors.CharacterDetailInteractor
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

    fun fetchData(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val response = characterInteractor.getCharacterById(id)) {
                is NetworkResult.Success -> {
                    characterLiveData.postValue(response.data!!)
                    makeShowState()
                }
                is NetworkResult.Error -> {
                    errorMessage = response.errorMessage ?: "Unknown Error"
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