package com.jetbrains.kmpapp.data

import kotlinx.serialization.Serializable

@Serializable
data class ContinentsData(
    val continents: List<Continent?>?,
)

@Serializable
data class Continent(
    var code: String?,
    var countries: List<Country?>?,
    var name: String?,
)

@Serializable
data class Country(
    var code: String?,
    var name: String?,
    var native: String? = "",
    var phone: String?,
)
