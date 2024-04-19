package com.muhammhassan.jkblc_starter_project.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.muhammhassan.jkblc_starter_project.core.model.Article
import com.muhammhassan.jkblc_starter_project.utils.RequestState

//TODO 8 : Lengkapi kelas ViewModel berikut ini
class MainViewModel {
    private val _newsState = MutableLiveData<RequestState<List<Article>>>()
    val newsState: LiveData<RequestState<List<Article>>> get() = _newsState

    fun getNews(query: String? = null) {
        _newsState.value = RequestState.Loading
    }
}