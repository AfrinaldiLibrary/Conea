package com.afrinaldi.conea.di

import com.afrinaldi.conea.data.repository.NewsRepository

object Injection {
    fun provideRepository(): NewsRepository {
        return NewsRepository.getInstance()
    }
}