package com.afrinaldi.conea.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afrinaldi.conea.data.HeroesRepository
import com.afrinaldi.conea.model.Heroes
import com.afrinaldi.conea.ui.common.UiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel (private val repository: HeroesRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Heroes>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Heroes>>>
        get() = _uiState

    fun getAllHeroes() {
        viewModelScope.launch {
            repository.getAllHeroes()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}