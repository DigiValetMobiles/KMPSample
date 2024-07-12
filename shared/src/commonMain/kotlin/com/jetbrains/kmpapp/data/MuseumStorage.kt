package com.jetbrains.kmpapp.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

interface MuseumStorage {
    suspend fun saveObjects(newObjects: List<MuseumObject>)

    fun getObjectById(objectId: Int): Flow<MuseumObject?>

    fun getObjects(): List<MuseumObject>

    fun clearObjects()
}

class InMemoryMuseumStorage : MuseumStorage {
    private val storedObjects = MutableStateFlow(emptyList<MuseumObject>())

    override suspend fun saveObjects(newObjects: List<MuseumObject>) {
        storedObjects.value = newObjects
    }

    override fun getObjectById(objectId: Int): Flow<MuseumObject?> {
        return storedObjects.map { objects ->
            objects.find { it.objectID == objectId }
        }
    }

    override fun getObjects(): List<MuseumObject> = storedObjects.value

    override fun clearObjects() {
        storedObjects.value = listOf()
    }
}
