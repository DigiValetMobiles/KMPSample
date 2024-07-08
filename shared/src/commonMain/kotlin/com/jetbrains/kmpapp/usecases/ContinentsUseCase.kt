package com.jetbrains.kmpapp.usecases

import com.jetbrains.kmpapp.data.Continent
import com.jetbrains.kmpapp.data.ContinentsData
import com.jetbrains.kmpapp.graphql.CountriesRepository
import com.jetbrains.kmpapp.local.AppPreferenceKeys
import com.jetbrains.kmpapp.local.DataPreferenceKeys
import com.jetbrains.kmpapp.local.DataStorePreferences
import com.jetbrains.kmpapp.utils.States
import kotlinx.serialization.json.Json

class ContinentsUseCase(private val countriesRepository: CountriesRepository) {

    suspend operator fun invoke(): States<List<Continent?>> {
        return if (DataStorePreferences.getFirstPreference(
                AppPreferenceKeys.GET_CONTINENTS_FROM_NETWORK, true
            )
        ) {
            countriesRepository.getContinents()
        } else {
            States.Success(
                Json.decodeFromString<ContinentsData>(
                    DataStorePreferences.getFirstPreference(
                        DataPreferenceKeys.GET_CONTINENTS_DATA, ""
                    )
                ).continents.orEmpty()
            )
        }
    }
}