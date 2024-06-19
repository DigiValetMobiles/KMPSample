package com.jetbrains.kmpapp

import com.jetbrains.kmpapp.data.MuseumRepository
import com.jetbrains.kmpapp.graphql.CountriesRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinDependencies : KoinComponent {
    val museumRepository: MuseumRepository by inject()
    val countriesRepository: CountriesRepository by inject()
}
