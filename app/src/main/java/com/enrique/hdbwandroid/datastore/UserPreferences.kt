package com.enrique.hdbwandroid.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class UserPreferences(context: Context) {

    private val dataStore = context.dataStore

    val userName: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[USER_NAME_KEY]
        }

    suspend fun saveUserName(name: String) {
        dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = name
        }
    }

    companion object {
        private val USER_NAME_KEY = stringPreferencesKey("user_name")
    }
}
