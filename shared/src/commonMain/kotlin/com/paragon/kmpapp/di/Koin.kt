package com.paragon.kmpapp.di

import com.paragon.kmpapp.data.InMemoryMuseumStorage
import com.paragon.kmpapp.data.KtorMuseumApi
import com.paragon.kmpapp.data.MuseumApi
import com.paragon.kmpapp.data.MuseumRepository
import com.paragon.kmpapp.data.MuseumStorage
import com.paragon.kmpapp.graphql.CountriesRepository
import com.paragon.kmpapp.usecases.ContinentsUseCase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

/**
 * expect function which will have different implementation in different platforms,
 * */
expect fun platformModule(): Module

val dataModule = module {

    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(ContentNegotiation) {
                // TODO Fix API so it serves application/json
                json(json, contentType = ContentType.Any)
            }
        }
    }

    single<MuseumApi> { KtorMuseumApi(get()) }
    single<MuseumStorage> { InMemoryMuseumStorage() }
    single {
        MuseumRepository(get(), get()).apply {
            initialize()
        }
    }

    single { CountriesRepository(get()) }
    single { ContinentsUseCase(get() , get()) }
}

fun initKoin() = initKoin(emptyList())

fun initKoin(extraModules: List<Module>, appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        modules(
            platformModule(),
            dataModule,
            *extraModules.toTypedArray(),
        )
    }
}
