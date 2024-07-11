package com.jetbrains.kmpapp.di

import com.jetbrains.kmpapp.graphql.CountriesRepository
import com.jetbrains.kmpapp.usecases.ContinentsUseCase
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

/**
 * expect function which will have different implementation in different platforms,
 * */
expect fun platformModule(): Module

val dataModule = module {
    single { CountriesRepository(get()) }
    single { ContinentsUseCase(get()) }
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
