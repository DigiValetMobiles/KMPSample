package com.paragon.kmpapp.di

import com.paragon.kmpapp.database.AppDatabase
import com.paragon.kmpapp.getDatabase
import org.koin.dsl.module

/**
 * actual implementation for platform module
 * */
actual fun platformModule() = module {
    single<AppDatabase> { getDatabase(get()) }
}
