package com.paragon.kmpapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.paragon.kmpapp.database.AppDatabase
import com.paragon.kmpapp.database.dbFileName
import com.paragon.kmpapp.local.DataStorePreferences
import com.paragon.kmpapp.local.DataStorePreferences.dataStoreFileName
import kotlinx.coroutines.Dispatchers

/**
 * Init datastore preference
 * */
fun initDataStore(context: Context): DataStore<Preferences> =
    DataStorePreferences.initDataStore(producePath = context.filesDir.resolve(dataStoreFileName).absolutePath)

/**
 * Room database builder
 * */
fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<AppDatabase> {
    val dbFile = context.getDatabasePath(dbFileName)
    return Room.databaseBuilder<AppDatabase>(context, dbFile.absolutePath)
        .setDriver(BundledSQLiteDriver()).setQueryCoroutineContext(Dispatchers.IO)
}

/**
 * Create room database
 * */
fun getDatabase(context: Context): AppDatabase {
    return getDatabaseBuilder(context).build()
}