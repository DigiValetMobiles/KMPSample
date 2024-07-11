package com.jetbrains.kmpapp.di

import com.jetbrains.kmpapp.database.AppDatabase
import com.jetbrains.kmpapp.getDatabase
import org.koin.dsl.module

/**
 * actual implementation for platform module
 * */
actual fun platformModule() = module {
    single<AppDatabase> { getDatabase() }
}

