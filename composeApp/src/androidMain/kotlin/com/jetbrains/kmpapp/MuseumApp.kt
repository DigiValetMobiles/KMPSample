package com.jetbrains.kmpapp

import android.app.Application
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.jetbrains.kmpapp.di.initKoin
import com.jetbrains.kmpapp.screens.CountriesViewModel
import org.koin.dsl.module

class MuseumApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            listOf(
                module {
                    factory { CountriesViewModel(get(), SqlNormalizedCacheFactory(this@MuseumApp, "apollo.db")) }
                }
            )
        )
    }
}
