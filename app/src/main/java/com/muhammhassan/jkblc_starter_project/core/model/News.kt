package com.muhammhassan.jkblc_starter_project.core.model

data class News(
    val articles: List<Article>, val status: String, val totalResults: Int
)
