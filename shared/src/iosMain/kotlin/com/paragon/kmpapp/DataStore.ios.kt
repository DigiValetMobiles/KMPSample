package com.paragon.kmpapp

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.paragon.kmpapp.database.AppDatabase
import com.paragon.kmpapp.database.dbFileName
import com.paragon.kmpapp.database.instantiateImpl
import com.paragon.kmpapp.local.DataStorePreferences.dataStoreFileName
import com.paragon.kmpapp.local.DataStorePreferences.initDataStore
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSHomeDirectory
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

/**
 * Init datastore preference
 * */
@OptIn(ExperimentalForeignApi::class)
fun createDataStore(): DataStore<Preferences> {
    val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return initDataStore(requireNotNull(documentDirectory).path + "/$dataStoreFileName")

}

/**
 * Room database builder
 * */
fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val dbFilePath = NSHomeDirectory() + "/${dbFileName}"
    return Room.databaseBuilder<AppDatabase>(name = dbFilePath,
        factory = { AppDatabase::class.instantiateImpl() }).setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
}

/**
 * Create room database
 * */
fun getDatabase(): AppDatabase {
    return getDatabaseBuilder().build()
}
