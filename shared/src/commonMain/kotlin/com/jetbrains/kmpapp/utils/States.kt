package com.jetbrains.kmpapp.utils


interface States<out T> {
    data object Loading : States<Nothing>
    data object Idle : States<Nothing>
    data class Success<out T>(val data: T) : States<T>
    data class Error(val error: HttpError) : States<Nothing>
}