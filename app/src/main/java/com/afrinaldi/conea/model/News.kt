package com.afrinaldi.conea.model

import com.google.gson.annotations.SerializedName

data class News(
    @field:SerializedName("totalResults")
    val totalResults: Int,

    @field:SerializedName("articles")
    val articles: List<ArticlesItem>,

    @field:SerializedName("status")
    val status: String
)
