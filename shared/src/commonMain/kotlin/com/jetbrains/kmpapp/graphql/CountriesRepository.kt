package com.jetbrains.kmpapp.graphql

import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.api.NormalizedCacheFactory
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import com.jetbrains.kmpapp.utils.HttpError
import com.jetbrains.kmpapp.utils.States

class CountriesRepository {

    suspend fun getContinents(cacheFactory: NormalizedCacheFactory): States<List<ContinentsQuery.Continent?>> {
        try {
            ApolloInstance.getApolloClient(cacheFactory).query(ContinentsQuery()).fetchPolicy(
                FetchPolicy.CacheFirst).execute().apply {
                return if (hasErrors()) {
                    States.Error(
                        HttpError.APOLLO_EXCEPTION.handleErrorCode(
                            errors?.get(0)?.nonStandardFields?.get(
                                "statusCode"
                            ).toString().toInt()
                        )
                    )
                } else States.Success(data?.continents.orEmpty())
            }
        } catch (e: Exception){
            return States.Error(
                HttpError.INTERNET_CONNECTION
            )
        }
    }

    suspend fun getCountries(cacheFactory: NormalizedCacheFactory, continentCode: String): States<ContinentDetailsQuery.Continent?> {
       try {
            ApolloInstance.getApolloClient(cacheFactory)
                .query(ContinentDetailsQuery(Optional.presentIfNotNull(continentCode))).fetchPolicy(
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
        } catch (e :Exception){
           print("==>>>>> $e")
           return States.Error(
               HttpError.INTERNET_CONNECTION
           )
        }
    }
}