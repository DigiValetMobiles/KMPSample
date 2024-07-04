package com.jetbrains.kmpapp.graphql

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.api.NormalizedCacheFactory
import com.apollographql.apollo3.cache.normalized.normalizedCache


internal object ApolloInstance {

    fun getApolloClient(cacheFactory: NormalizedCacheFactory): ApolloClient {
        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .normalizedCache(cacheFactory)
            .build()
        return apolloClient
    }
}