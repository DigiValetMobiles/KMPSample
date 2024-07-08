/*
 * Copyright 2023 DigiValet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jetbrains.kmpapp.local

import androidx.datastore.preferences.core.booleanPreferencesKey

/**
 * The enum Preference keys.
 */
object AppPreferenceKeys {

    /*
    * Caching flags...
    * */
    val GET_CONTINENTS_FROM_NETWORK = booleanPreferencesKey("get_continents_from_network")
    val GET_COUNTRIES_FROM_NETWORK = booleanPreferencesKey("get_countries_from_network")
}