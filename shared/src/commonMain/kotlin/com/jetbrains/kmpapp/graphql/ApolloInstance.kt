package com.jetbrains.kmpapp.graphql

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.api.NormalizedCacheFactory
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory


internal object ApolloInstance {

    /**
     * Get apollo client
     * */
    fun getApolloClient(): ApolloClient {
        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .normalizedCache(SqlNormalizedCacheFactory("apollo.db"))
            .build()
        return apolloClient
    }
}