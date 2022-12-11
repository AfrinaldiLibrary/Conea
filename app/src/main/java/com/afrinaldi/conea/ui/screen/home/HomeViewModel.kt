package com.afrinaldi.conea.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

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

    fun searchHero(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            repository.searchHeroes(_query.value).collect{
                _uiState.value = UiState.Success(if (_query.value.isNotEmpty()){
                    it.filter { data ->
                        data.name!![0].lowercaseChar() == _query.value[0].lowercaseChar()
                    }
                } else {
                    it
                })
            }
        }
    }
}