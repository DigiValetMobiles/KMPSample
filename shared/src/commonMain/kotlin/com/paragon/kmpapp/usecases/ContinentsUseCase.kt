package com.paragon.kmpapp.usecases

import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.apolloStore
import com.paragon.kmpapp.data.Continent
import com.paragon.kmpapp.data.ContinentsData
import com.paragon.kmpapp.data.MuseumObject
import com.paragon.kmpapp.data.MuseumRepository
import com.paragon.kmpapp.graphql.ApolloInstance
import com.paragon.kmpapp.graphql.CountriesRepository
import com.paragon.kmpapp.local.AppPreferenceKeys
import com.paragon.kmpapp.local.DataPreferenceKeys
import com.paragon.kmpapp.local.DataStorePreferences
import com.paragon.kmpapp.utils.FetchType
import com.paragon.kmpapp.utils.States
import kotlinx.serialization.json.Json

/**
 * Continents Use Case
 * */
class ContinentsUseCase(
    private val countriesRepository: CountriesRepository,
    private val museumRepository: MuseumRepository
) {

    /**
     * Get list of all continents and their data
     * */
    suspend fun getContinentsData(fetchType: String): States<List<Continent?>> {

        /**
         * Get Data according to the fetchType
         * */
        return when (fetchType) {

            FetchType.GraphQl.value -> {
                countriesRepository.getContinents(FetchPolicy.NetworkOnly)
            }

            FetchType.GraphQlCaching.value -> {
                countriesRepository.getContinents(FetchPolicy.CacheOnly)
            }

            FetchType.RoomCaching.value -> {
                States.Success(countriesRepository.appDatabase.countriesDao().getContinents())
            }

            FetchType.DataStorePreferencesCaching.value -> {
                if (DataStorePreferences.getFirstPreference(
                        AppPreferenceKeys.GET_CONTINENTS_FROM_NETWORK, true
                    )
                ) States.Success(listOf()) else States.Success(
                    Json.decodeFromString<ContinentsData>(
                        DataStorePreferences.getFirstPreference(
                            DataPreferenceKeys.GET_CONTINENTS_DATA, ""
                        )
                    ).continents.orEmpty()
                )
            }

            FetchType.KTOR.value -> {
                States.Success(getObjects().map {
                    Continent(
                        code = it.title,
                        countries = listOf(),
                        name = it.artistDisplayName,
                    )
                })
            }

            else -> {
                States.Success(listOf())
            }
        }
    }

    /**
     * Get museum objects list
     * */
    fun getObjects(): List<MuseumObject> {
        return museumRepository.getObjectsFromNetwork()
    }


    /**
     * Clear All Cache
     * */
    suspend fun clearCache() {
        DataStorePreferences.clearAllPreference()
        ApolloInstance.getApolloClient().apolloStore.clearAll()
        countriesRepository.appDatabase.countriesDao().deleteContinents()
        museumRepository.clearObject()
    }
}