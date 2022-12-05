package com.afrinaldi.conea.data.repository

import com.afrinaldi.conea.model.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class NewsRepository {

    private val news = mutableListOf<News>()

    fun getAllNews(): Flow<List<News>> {
        return flowOf(news)
    }

    companion object{
        @Volatile
        private var instance: NewsRepository? = null

        fun getInstance(): NewsRepository =
            instance ?: synchronized(this) {
                NewsRepository().apply {
                    instance = this
                }
            }
    }
}