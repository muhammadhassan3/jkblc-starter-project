package com.muhammhassan.jkblc_starter_project.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muhammhassan.jkblc_starter_project.core.api.ApiClient
import com.muhammhassan.jkblc_starter_project.core.model.Article
import com.muhammhassan.jkblc_starter_project.core.model.News
import com.muhammhassan.jkblc_starter_project.utils.RequestState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//TODO 8 : Lengkapi kelas ViewModel berikut ini
class MainViewModel: ViewModel() {
    private val _newsState = MutableLiveData<RequestState<List<Article>>>()
    val newsState: LiveData<RequestState<List<Article>>> get() = _newsState
    private val api by lazy {
        ApiClient.getInstance()
    }

    init {
        getNews()
    }

    fun getNews(query: String? = null){
        _newsState.value = RequestState.Loading
        val request = api.getTopHeadlines(query = query, country = "us")
        request.enqueue(object: Callback<News>{
            override fun onResponse(p0: Call<News>, response: Response<News>) {
                if(response.isSuccessful){
                    val articles = response.body()!!.articles
                    if(articles.isNotEmpty()){
                        _newsState.postValue(RequestState.Success(articles))
                    }else _newsState.postValue(RequestState.Error("Artikel tidak ditemukan"))
                }else{
                    println(response.errorBody())
                    _newsState.postValue(RequestState.Error("Gagal memuat data"))
                }
            }

            override fun onFailure(p0: Call<News>, p1: Throwable) {
                _newsState.postValue(RequestState.Error("Gagal memuat data"))
            }

        })
    }
}