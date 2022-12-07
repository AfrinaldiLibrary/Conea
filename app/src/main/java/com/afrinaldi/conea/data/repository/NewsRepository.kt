package com.afrinaldi.conea.data.repository

import com.afrinaldi.conea.data.network.ApiService
import com.afrinaldi.conea.model.ArticlesItem
import com.afrinaldi.conea.data.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepository (
    private val apiService: ApiService,
    ) {

    fun getBreakingNews(): Flow<Result<List<ArticlesItem>>> = flow {
        emit(Result.Loading)
        try {
            val response = apiService.getBreaking()
            val articles = response.articles
            val newsList = articles.map {
                ArticlesItem(
                    it.publishedAt,
                    it.author,
                    it.urlToImage,
                    it.description,
                    it.source,
                    it.title,
                    it.url,
                    it.content,
                )
            }
            emit(Result.Success(newsList))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object{
        @Volatile
        private var instance: NewsRepository? = null

        fun getInstance(apiService: ApiService): NewsRepository =
            instance ?: synchronized(this) {
                NewsRepository(apiService).apply {
                    instance = this
                }
            }
    }
}