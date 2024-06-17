package com.jetbrains.kmpapp.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.kmpapp.data.MuseumObject
import com.jetbrains.kmpapp.graphql.ContinentsQuery
import com.jetbrains.kmpapp.graphql.CountriesRepository
import com.jetbrains.kmpapp.utils.States
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class CountriesViewModel(private val countriesRepository: CountriesRepository) : ViewModel() {
    private val _continents: MutableStateFlow<States<List<ContinentsQuery.Continent?>>> =
        MutableStateFlow(States.Idle)
    val continents: StateFlow<States<List<ContinentsQuery.Continent?>>> = _continents
//    .flatMapLatest {
//        _continents.value = it
//            countriesRepository.getContinents()
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)


    fun getContinents() {
//        _continents.value = States.Loading
        viewModelScope.launch {
            _continents.value = countriesRepository.getContinents()
        }
    }
}
