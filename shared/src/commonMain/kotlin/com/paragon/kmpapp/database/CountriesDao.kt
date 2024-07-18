package com.paragon.kmpapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverter
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.api.json.buildJsonString
import com.apollographql.apollo3.api.toJson
import com.paragon.kmpapp.data.Continent
import com.paragon.kmpapp.data.Country
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Dao
interface CountriesDao {

    @Query("SELECT * FROM Continent")
    suspend fun getContinents(): List<Continent>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContinent(continent: Continent)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContinents(continents: List<Continent>)

    @Query("DELETE FROM Continent")
    suspend fun deleteContinents()

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
    fun toJson(list: List<Country?>): String {
        return Json.encodeToString(list)
    }
}