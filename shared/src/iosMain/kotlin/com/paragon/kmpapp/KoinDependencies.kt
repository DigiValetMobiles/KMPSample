package com.paragon.kmpapp

import com.paragon.kmpapp.graphql.CountriesRepository
import com.paragon.kmpapp.usecases.ContinentsUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinDependencies : KoinComponent {
    val countriesRepository: CountriesRepository by inject()
    val continentsUseCase : ContinentsUseCase by inject()
}
