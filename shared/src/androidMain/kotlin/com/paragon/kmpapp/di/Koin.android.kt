package com.paragon.kmpapp.di

import com.paragon.kmpapp.createRoomDatabase
import com.paragon.kmpapp.database.AppDatabase
import org.koin.dsl.module

/**
 * actual implementation for platform module
 * */
actual fun platformModule() = module {
    single<AppDatabase> { createRoomDatabase(get()) }
}
