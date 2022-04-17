package com.example.rickandmortypersons.presentation.feature.detail

import android.util.Log
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
                    Log.d("CharactersViewModel", response.data!!.toString())
                    characterLiveData.postValue(response.data!!)
                    makeShowState()
                }
                is NetworkResult.Error -> {
                    Log.d("CharactersViewModel", response.toString())
                    errorMessage = response.errorMessage ?: "Unknown Error"
                    makeErrorState()
                }
            }
        }
    }

    fun makeShowState() {
        state.postValue(CharacterDetailState.SHOW)
    }

    fun makeErrorState() {
        state.postValue(CharacterDetailState.ERROR)
    }
}