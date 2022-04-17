package com.example.rickandmortypersons.presentation.feature.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortypersons.data.entities.Character
import com.example.rickandmortypersons.data.network_utils.NetworkResult
import com.example.rickandmortypersons.domain.interactors.CharacterInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterDetailViewModel : ViewModel(), KoinComponent {
    private val characterInteractor by inject<CharacterInteractor>()
    var errorMessage = ""
    val characterLiveData = MutableLiveData<Character>()
    val state = MutableLiveData(CharacterDetailState.LOADING)

    fun fetchData(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = characterInteractor.getCharacterById(id)
            when (response) {
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
}