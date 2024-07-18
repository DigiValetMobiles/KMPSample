package com.paragon.kmpapp.screens.contientdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paragon.kmpapp.graphql.ContinentDetailsQuery
import com.paragon.kmpapp.graphql.CountriesRepository
import com.paragon.kmpapp.utils.States
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Details view Model
 * */
class ContinentDetailViewModel(
    private val countriesRepository: CountriesRepository
) : ViewModel() {

    private val _countries: MutableStateFlow<States<ContinentDetailsQuery.Continent?>> =
        MutableStateFlow(States.Idle)
    val countries: StateFlow<States<ContinentDetailsQuery.Continent?>> = _countries

    /**
     * Function to get all countries of respective continent
     * */
    fun getCountries(continentCode: String) {
        viewModelScope.launch {
            _countries.value = countriesRepository.getCountries(continentCode)
        }
    }
}
