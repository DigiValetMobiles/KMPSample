package com.jetbrains.kmpapp.graphql

import com.apollographql.apollo3.ApolloClient


internal object ApolloInstance {

    fun getApolloClient(): ApolloClient {
        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()
        return apolloClient
    }
}