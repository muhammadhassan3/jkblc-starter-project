package com.muhammhassan.jkblc_starter_project.core.api

import com.muhammhassan.jkblc_starter_project.BuildConfig
import com.muhammhassan.jkblc_starter_project.core.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsEndpoint {
    //TODO 7 : Definisikan Endpoint yang digunakan

    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String = "id",
        @Query("q") query: String? = null,
        @Query("apiKey") key: String = BuildConfig.API_KEY
    ): Call<News>
}