package com.jetbrains.kmpapp.screens.cachinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.kmpapp.usecases.ContinentsUseCase
import kotlinx.coroutines.launch

/**
 * Caching List ViewModel
 * */
class CachingListViewModel(private val continentsUseCase: ContinentsUseCase) : ViewModel() {

    /**
     * Clear all cache
     * */
    fun clearCache() {
        viewModelScope.launch {
            continentsUseCase.clearCache()
        }
    }
}