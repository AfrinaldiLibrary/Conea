package com.afrinaldi.conea.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.afrinaldi.conea.data.HeroesRepository
import com.afrinaldi.conea.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: HeroesRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}