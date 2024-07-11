package com.jetbrains.kmpapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.jetbrains.kmpapp.database.AppDatabase
import com.jetbrains.kmpapp.database.dbFileName
import com.jetbrains.kmpapp.local.DataStorePreferences
import com.jetbrains.kmpapp.local.DataStorePreferences.dataStoreFileName
import kotlinx.coroutines.Dispatchers

/**
* Init datastore preference
* */
fun initDataStore(context: Context): DataStore<Preferences> =
    DataStorePreferences.initDataStore(producePath = context.filesDir.resolve(dataStoreFileName).absolutePath)

/**
* Create room database
* */
fun createRoomDatabase(context: Context): AppDatabase {
    val dbFile = context.getDatabasePath(dbFileName)
    return Room.databaseBuilder<AppDatabase>(context, dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}