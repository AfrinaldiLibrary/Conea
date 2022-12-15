package com.afrinaldi.conea.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afrinaldi.conea.data.HeroesRepository
import com.afrinaldi.conea.model.Heroes
import com.afrinaldi.conea.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.switchMap
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: HeroesRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<Heroes>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Heroes>> get() = _uiState

    fun getSelectedHero(name: String){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getSelectedHero(name))
        }
    }

    fun changeStatusFavorite(heroes : Heroes, isFavorites: Boolean) {
        viewModelScope.launch {
            repository.updateStatusFavorite(heroes.name!!, isFavorites)
        }
    }
}