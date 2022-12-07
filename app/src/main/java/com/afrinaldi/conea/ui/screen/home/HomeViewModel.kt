package com.afrinaldi.conea.ui.screen.home

import androidx.lifecycle.ViewModel
import com.afrinaldi.conea.data.repository.NewsRepository

class HomeViewModel (private val repository: NewsRepository) : ViewModel() {
    fun getAllNews() = repository.getBreakingNews()
}