package com.jetbrains.kmpapp.screens

import com.jetbrains.kmpapp.data.MuseumObject
import com.jetbrains.kmpapp.data.MuseumRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow

//class ListViewModel(museumRepository: MuseumRepository) : ViewModel() {
//    @NativeCoroutinesState
//    val objects: StateFlow<List<MuseumObject>> =
//        museumRepository.getObjects()
//            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
//}
