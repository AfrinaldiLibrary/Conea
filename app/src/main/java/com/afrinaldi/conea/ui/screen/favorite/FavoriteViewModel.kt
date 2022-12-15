package com.afrinaldi.conea.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afrinaldi.conea.data.HeroesRepository
import com.afrinaldi.conea.model.Heroes
import com.afrinaldi.conea.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: HeroesRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Heroes>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Heroes>>>
        get() = _uiState

    fun getFavorites() {
        viewModelScope.launch {
            repository.getFavorite()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}