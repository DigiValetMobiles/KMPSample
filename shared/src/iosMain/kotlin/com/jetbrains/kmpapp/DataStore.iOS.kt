package com.jetbrains.kmpapp

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.jetbrains.kmpapp.local.DataStorePreferences.dataStoreFileName
import com.jetbrains.kmpapp.local.DataStorePreferences.initDataStore

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
fun createDataStore(): DataStore<Preferences> {
    val documentDirectory: NSURL = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return initDataStore(requireNotNull(documentDirectory).path + "/$dataStoreFileName")
}