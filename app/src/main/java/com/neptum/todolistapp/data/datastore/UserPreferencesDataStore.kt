package com.neptum.todolistapp.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferencesDataStore(
    private val context: Context
) {
    companion object {
        private val DATA_NAME = stringPreferencesKey("name")
        private val DATA_PHOTO = stringPreferencesKey("photo")
        private val DATA_THEME = stringPreferencesKey("theme")
    }

    val current: Flow<UserPreference> = context
        .dataStore
        .data
        .map { preferences ->
            UserPreference(
                name = preferences[DATA_NAME] ?: "Desconhecido",
                photoUrl = preferences[DATA_PHOTO],
                theme = preferences[DATA_THEME]?.let {
                    AppTheme.valueOf(it)
                } ?: AppTheme.SYSTEM
            )
        }

    suspend fun saveName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[DATA_NAME] = name
        }
    }

    fun savePhoto() {

    }

    fun saveTheme() {

    }
}