package com.jetbrains.kmpapp.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.jetbrains.kmpapp.graphql.ContinentDetailsQuery
import com.jetbrains.kmpapp.graphql.ContinentsQuery
import com.jetbrains.kmpapp.graphql.CountriesRepository
import com.jetbrains.kmpapp.utils.States
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CountriesViewModel(
    private val countriesRepository: CountriesRepository,
    private val sqlNormalizedCacheFactory: SqlNormalizedCacheFactory
) : ViewModel() {
    private val _continents: MutableStateFlow<States<List<ContinentsQuery.Continent?>>> =
        MutableStateFlow(States.Idle)
    val continents: StateFlow<States<List<ContinentsQuery.Continent?>>> = _continents

    private val _countries: MutableStateFlow<States<ContinentDetailsQuery.Continent?>> =
        MutableStateFlow(States.Idle)
    val countries: StateFlow<States<ContinentDetailsQuery.Continent?>> = _countries
//    .flatMapLatest {
//        _continents.value = it
//            countriesRepository.getContinents()
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)


    fun getContinents(s:String) {
//        _continents.value = States.Loading
        viewModelScope.launch {
            _continents.value = countriesRepository.getContinents(sqlNormalizedCacheFactory)
        }
    }

    fun getCountries(continentCode: String) {
//        _continents.value = States.Loading
        viewModelScope.launch {
            _countries.value =
                countriesRepository.getCountries(sqlNormalizedCacheFactory, continentCode)
        }
    }
}
