package com.jetbrains.kmpapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.TypeConverter
import androidx.room.Upsert
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.api.json.buildJsonString
import com.apollographql.apollo3.api.toJson
import com.jetbrains.kmpapp.data.Continent
import com.jetbrains.kmpapp.data.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Dao
interface CountriesDao {

    @Query("SELECT * FROM Continent")
    suspend fun getContinents(): List<Continent>

    @Insert(Continent::class)
    suspend fun insertContinents(vararg continents: Continent)

}

class MyTypeConverter {
    @TypeConverter
    fun encodeToJsonString(value: Operation.Data): String {
        return buildJsonString { value.toJson(this) }
    }

    @TypeConverter
    fun continentFromJsonString(json: String): Continent {
        return Json.decodeFromString(json)
    }

    @TypeConverter
    fun countryFromJsonString(json: String): Country {
        return Json.decodeFromString(json)
    }

    @TypeConverter
    fun fromJson(value: String): List<Country> {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun toJson(list: List<*>): String {
        return Json.encodeToString(list)
    }
}