package com.afrinaldi.conea.data.network

import com.afrinaldi.conea.BuildConfig.API_KEY
import com.afrinaldi.conea.model.News
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getBreaking(
        @Query("country") country: String = "id",
        @Query("apiKey") apiKey: String = API_KEY
    ): News

    @GET("top-headlines")
    suspend fun getTechnology(
        @Query("country") country: String = "id",
        @Query("category") category: String = "technology",
        @Query("apiKey") apiKey: String = API_KEY
    ): News
}