package com.jetbrains.kmpapp.screens.continentslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.kmpapp.data.Continent
import com.jetbrains.kmpapp.data.MuseumObject
import com.jetbrains.kmpapp.data.MuseumRepository
import com.jetbrains.kmpapp.usecases.ContinentsUseCase
import com.jetbrains.kmpapp.utils.FetchType
import com.jetbrains.kmpapp.utils.States
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
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
