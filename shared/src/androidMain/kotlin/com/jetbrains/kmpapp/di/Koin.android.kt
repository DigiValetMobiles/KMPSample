package com.jetbrains.kmpapp.di

import com.jetbrains.kmpapp.createRoomDatabase
import com.jetbrains.kmpapp.database.AppDatabase
import org.koin.dsl.module

/**
 * actual implementation for platform module
 * */
actual fun platformModule() = module {
    single<AppDatabase> { createRoomDatabase(get()) }
}
