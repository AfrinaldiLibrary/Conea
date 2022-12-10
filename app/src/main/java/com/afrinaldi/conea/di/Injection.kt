package com.afrinaldi.conea.di

import com.afrinaldi.conea.data.HeroesRepository

object Injection {
    fun provideRepository(): HeroesRepository {
        return HeroesRepository.getInstance()
    }
}