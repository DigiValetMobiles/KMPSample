package com.paragon.kmpapp

import android.app.Application
import com.paragon.kmpapp.di.initKoin
import com.paragon.kmpapp.screens.cachinglist.CachingListViewModel
import com.paragon.kmpapp.screens.contientdetail.ContinentDetailViewModel
import com.paragon.kmpapp.screens.continentslist.ContinentsListViewModel
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
        com.paragon.kmpapp.initDataStore(baseContext)
    }
}
