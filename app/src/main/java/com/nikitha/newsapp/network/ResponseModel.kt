package com.nikitha.newsapp.network


data class ResponseModel(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleModel>
)
