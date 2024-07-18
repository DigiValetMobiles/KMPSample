package com.paragon.kmpapp.local

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import okio.Path.Companion.toPath

object DataStorePreferences {

    private lateinit var dataStore: DataStore<Preferences>
    internal const val dataStoreFileName = "PreferenceDataStore.preferences_pb"

    /**
     * Gets the singleton DataStore instance, creating it if necessary.
     */
    fun initDataStore(producePath: String): DataStore<Preferences> =
        if (::dataStore.isInitialized) {
            dataStore
        } else {
            PreferenceDataStoreFactory.createWithPath(produceFile = { producePath.toPath() })
                .also { dataStore = it }
        }


    /** This returns us a flow of data from DataStore.
     * As soon we update the value in Datastore,
     * the values returned by it also changes.
     */
    suspend fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): Flow<T> =
        dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            val result = preferences[key] ?: defaultValue
            result
        }

    /**
     * This returns the last saved value of the key. If we change the value,
     * it wont effect the values produced by this function
     **/
    suspend fun <T> getFirstPreference(key: Preferences.Key<T>, defaultValue: T): T =
        dataStore.data.first()[key] ?: defaultValue

    /**
     * This Sets the value based on the value passed in value parameter.
     * */
    suspend fun <T> addPreference(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    /**
     * This Function removes the Key Value pair from the datastore,
     * hereby removing it completely.
     * **/
    suspend fun <T> removePreference(key: Preferences.Key<T>) {
        dataStore.edit { preferences ->
            preferences.remove(key)
        }
    }

    /**
     * This function clears the entire Preference Datastore.
     * **/
    suspend fun clearAllPreference() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }


}