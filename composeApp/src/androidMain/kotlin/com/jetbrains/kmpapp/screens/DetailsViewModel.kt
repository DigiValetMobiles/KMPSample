package com.jetbrains.kmpapp.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.jetbrains.kmpapp.graphql.ContinentDetailsQuery
import com.jetbrains.kmpapp.graphql.CountriesRepository
import com.jetbrains.kmpapp.utils.States
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val countriesRepository: CountriesRepository,
    private val sqlNormalizedCacheFactory: SqlNormalizedCacheFactory
) : ViewModel() {

    private val _countries: MutableStateFlow<States<ContinentDetailsQuery.Continent?>> =
        MutableStateFlow(States.Idle)
    val countries: StateFlow<States<ContinentDetailsQuery.Continent?>> = _countries

    fun getCountries(continentCode: String) {
        viewModelScope.launch {
            _countries.value =
                countriesRepository.getCountries(sqlNormalizedCacheFactory, continentCode)
        }
    }
}
