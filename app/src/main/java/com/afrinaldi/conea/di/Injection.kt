package com.afrinaldi.conea.di

import com.afrinaldi.conea.data.network.ApiConfig
import com.afrinaldi.conea.data.repository.NewsRepository

object Injection {
    fun provideRepository(): NewsRepository {
        val apiService = ApiConfig.getApiService()
        return NewsRepository.getInstance(apiService)
    }
}