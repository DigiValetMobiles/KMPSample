package com.jetbrains.kmpapp

import android.app.Application
import com.jetbrains.kmpapp.di.initKoin
import com.jetbrains.kmpapp.screens.cachinglist.CachingListViewModel
import com.jetbrains.kmpapp.screens.contientdetail.ContinentDetailViewModel
import com.jetbrains.kmpapp.screens.continentslist.ContinentsListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * KMP application class
 * */
class KmpApp : Application() {

    /**
     * Initialize Koin Dependencies and Data Store
     * */
    override fun onCreate() {
        super.onCreate()
        initKoin(listOf(module {
            factory { CachingListViewModel(get()) }
            factory { ContinentsListViewModel(get()) }
            factory { ContinentDetailViewModel(get()) }
        })) {
            androidContext(applicationContext)
        }
        initDataStore(baseContext)
    }
}
