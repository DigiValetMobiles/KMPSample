package com.jetbrains.kmpapp

import android.app.Application
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.jetbrains.kmpapp.di.initKoin
import com.jetbrains.kmpapp.screens.ContinentsViewModel
import com.jetbrains.kmpapp.screens.DetailsViewModel
import org.koin.dsl.module
import org.koin.android.ext.koin.androidContext

class KmpApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            listOf(
                module {
//                    single<AppDatabase> { createRoomDatabase(get()) }
                    factory { ContinentsViewModel(get(), SqlNormalizedCacheFactory(this@KmpApp, "apollo.db")) }
                    factory { DetailsViewModel(get(), SqlNormalizedCacheFactory(this@KmpApp, "apollo.db")) }
                }
            )
        ){
            androidContext(applicationContext)
        }
        initDataStore(baseContext)
//        createRoomDatabase(baseContext)
    }
}
