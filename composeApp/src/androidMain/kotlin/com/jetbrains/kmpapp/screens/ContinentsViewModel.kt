package com.jetbrains.kmpapp.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.jetbrains.kmpapp.data.Continent
import com.jetbrains.kmpapp.graphql.CountriesRepository
import com.jetbrains.kmpapp.utils.States
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ContinentsViewModel(
    private val countriesRepository: CountriesRepository,
    private val sqlNormalizedCacheFactory: SqlNormalizedCacheFactory
) : ViewModel() {
    private val _continents: MutableStateFlow<States<List<Continent?>>> =
        MutableStateFlow(States.Idle)
    val continents: StateFlow<States<List<Continent?>>> = _continents

    init {
        getContinents()
    }


    fun getContinents() {
        viewModelScope.launch {
            _continents.value = countriesRepository.getContinents()
        }
    }

}
