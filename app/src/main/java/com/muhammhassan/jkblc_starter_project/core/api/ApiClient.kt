package com.muhammhassan.jkblc_starter_project.core.api

import com.muhammhassan.jkblc_starter_project.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object{
        private var INSTANCE: NewsEndpoint? = null
        private val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        fun getInstance(): NewsEndpoint = INSTANCE ?: synchronized(this) {
            //TODO 6 : Inisiasi object retrofit
            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            val instance = retrofit.create(NewsEndpoint::class.java)
            INSTANCE = instance
            instance
        }
    }
}