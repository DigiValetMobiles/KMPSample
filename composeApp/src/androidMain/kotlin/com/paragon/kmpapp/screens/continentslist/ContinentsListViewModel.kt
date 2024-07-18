package com.paragon.kmpapp.screens.continentslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paragon.kmpapp.data.Continent
import com.paragon.kmpapp.usecases.ContinentsUseCase
import com.paragon.kmpapp.utils.FetchType
import com.paragon.kmpapp.utils.States
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Continents List View Model
 * */
class ContinentsListViewModel(
    private val continentsUseCase: ContinentsUseCase
) : ViewModel() {
    private val _continents: MutableStateFlow<States<List<Continent?>>> =
        MutableStateFlow(States.Idle)
    val continents: StateFlow<States<List<Continent?>>> = _continents

    /**
     * Get list of continents
     * */
    fun getContinents(fetchType: String = FetchType.GraphQl.value) {
        viewModelScope.launch {
            _continents.value = continentsUseCase.getContinentsData(fetchType)
        }
    }

}
