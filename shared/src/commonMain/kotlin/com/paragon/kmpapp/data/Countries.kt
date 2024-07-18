package com.paragon.kmpapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class ContinentsData(
    val continents: List<Continent?>?,
)

@Serializable
@Entity
data class Continent(
    @PrimaryKey val code: String,
    var countries: List<Country?>?,
    var name: String?,
)

@Serializable
@Entity
data class Country(
    @PrimaryKey val code: String,
    var name: String?,
    var native: String? = "",
    var phone: String?,
)
