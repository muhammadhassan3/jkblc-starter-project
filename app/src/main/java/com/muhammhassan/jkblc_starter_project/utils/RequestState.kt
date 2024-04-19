package com.muhammhassan.jkblc_starter_project.utils

sealed class RequestState<out T> {
    data class Success<T>(val data: T): RequestState<T>()
    data object Loading: RequestState<Nothing>()
    data class Error(val message: String): RequestState<Nothing>()

}