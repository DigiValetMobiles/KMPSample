package com.jetbrains.kmpapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.jetbrains.kmpapp.local.DataStorePreferences
import com.jetbrains.kmpapp.local.DataStorePreferences.dataStoreFileName


fun initDataStore(context: Context): DataStore<Preferences> =
    DataStorePreferences.initDataStore(producePath = context.filesDir.resolve(dataStoreFileName).absolutePath )