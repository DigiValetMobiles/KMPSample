package com.jetbrains.kmpapp.utils

enum class FetchType(val value: String) {
    GraphQl("GraphQl"),
    GraphQlCaching("GraphQlCaching"),
    RoomCaching("RoomCaching"),
    DataStorePreferencesCaching("DataStorePreferencesCaching"),
    KTOR("KTOR");
}
