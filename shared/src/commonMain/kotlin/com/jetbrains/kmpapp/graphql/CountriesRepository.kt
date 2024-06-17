package com.jetbrains.kmpapp.graphql

import com.jetbrains.kmpapp.utils.HttpError
import com.jetbrains.kmpapp.utils.States

class CountriesRepository {

    suspend fun getContinents(): States<List<ContinentsQuery.Continent?>> {
        ApolloInstance.getApolloClient().query(ContinentsQuery()).execute().apply {
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
    }

    suspend fun getCountries(): States<List<ContinentsQuery.Continent?>> {
        ApolloInstance.getApolloClient().query(ContinentsQuery()).execute().apply {
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
    }
}