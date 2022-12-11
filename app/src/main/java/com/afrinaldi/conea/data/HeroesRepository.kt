package com.afrinaldi.conea.data

import com.afrinaldi.conea.model.Heroes
import com.afrinaldi.conea.model.HeroesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class HeroesRepository {
    private val heroes = mutableListOf<Heroes>()

    init {
        if (heroes.isEmpty()) {
            heroes.addAll(HeroesDataSource.listData)
        }
    }

    fun getAllHeroes(): Flow<List<Heroes>> {
        return flowOf(heroes)
    }

    fun getSelectedHero(name: String): Heroes {
        return heroes.first {
            it.name == name
        }
    }

    fun searchHeroes(query: String): Flow<List<Heroes>> {
        return flowOf(heroes.filter {
            it.name!!.contains(query, ignoreCase = true)
        })
    }

    companion object {
        @Volatile
        private var instance: HeroesRepository? = null

        fun getInstance(): HeroesRepository =
            instance ?: synchronized(this) {
                HeroesRepository().apply {
                    instance = this
                }
            }
    }
}