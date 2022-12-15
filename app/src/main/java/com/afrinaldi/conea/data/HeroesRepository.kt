package com.afrinaldi.conea.data

import com.afrinaldi.conea.model.Heroes
import com.afrinaldi.conea.model.HeroesDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

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

    fun getFavorite(): Flow<List<Heroes>> {
        return getAllHeroes()
            .map { heroes ->
                heroes.filter {
                    it.isFavorite
                }
            }
    }

    fun updateStatusFavorite(name: String, isFavorite: Boolean) : Flow<Boolean>{
        val index = heroes.indexOfFirst { it.name == name }
        val result = if (index >= 0) {
            val hero = heroes[index]
            heroes[index] = hero.copy(name = name, isFavorite = isFavorite)
            true
        } else {
            false
        }
        return flowOf(result)
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