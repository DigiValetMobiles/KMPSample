package com.jetbrains.kmpapp.graphql

import com.apollographql.apollo3.api.json.buildJsonString
import com.apollographql.apollo3.api.toJson
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.api.NormalizedCacheFactory
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import com.jetbrains.kmpapp.data.Continent
import com.jetbrains.kmpapp.data.ContinentsData
import com.jetbrains.kmpapp.utils.HttpError
import com.jetbrains.kmpapp.utils.States
import kotlinx.serialization.json.Json

class CountriesRepository {

    suspend fun getContinents(): States<List<Continent?>> {
        try {
            ApolloInstance.getApolloClient().query(ContinentsQuery()).fetchPolicy(
                FetchPolicy.CacheFirst
            ).execute().apply {
                return if (hasErrors()) {
                    States.Error(
                        HttpError.APOLLO_EXCEPTION.handleErrorCode(
                            errors?.get(0)?.nonStandardFields?.get(
                                "statusCode"
                            ).toString().toInt()
                        )
                    )
                } else {
                    val json = buildJsonString { data?.toJson(this) }
                    States.Success(Json.decodeFromString<ContinentsData>(json).continents.orEmpty())
                }
            }
        } catch (e: Exception) {
            return States.Error(
                HttpError.INTERNET_CONNECTION
            )
        }
    }

    suspend fun getCountries(
        cacheFactory: NormalizedCacheFactory,
        continentCode: String
    ): States<ContinentDetailsQuery.Continent?> {
        try {
            ApolloInstance.getApolloClient().query(ContinentDetailsQuery(continentCode))
                .fetchPolicy(
                    FetchPolicy.CacheFirst
                ).execute().apply {
                    return if (hasErrors()) {
                        States.Error(
                            HttpError.APOLLO_EXCEPTION.handleErrorCode(
                                errors?.get(0)?.nonStandardFields?.get(
                                    "statusCode"
                                ).toString().toInt()
                            )
                        )
                    } else States.Success(data?.continent)
                }
        } catch (e: Exception) {
            print("==>>>>> $e")
            return States.Error(
                HttpError.INTERNET_CONNECTION
            )
        }
    }
}