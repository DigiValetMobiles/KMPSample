package com.jetbrains.kmpapp

import com.jetbrains.kmpapp.graphql.CountriesRepository
import com.jetbrains.kmpapp.usecases.ContinentsUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinDependencies : KoinComponent {
    val countriesRepository: CountriesRepository by inject()
    val continentsUseCase : ContinentsUseCase by inject()
}
