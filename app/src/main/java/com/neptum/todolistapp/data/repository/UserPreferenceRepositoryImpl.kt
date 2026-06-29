package com.neptum.todolistapp.data.repository

import com.neptum.todolistapp.data.datastore.AppTheme
import com.neptum.todolistapp.data.datastore.UserPreference
import com.neptum.todolistapp.data.datastore.UserPreferencesDataStore
import com.neptum.todolistapp.repository.UserPreferenceRepository
import kotlinx.coroutines.flow.Flow

class UserPreferenceRepositoryImpl(
    private val userPreferencesDataStore: UserPreferencesDataStore
): UserPreferenceRepository {
    override val preference: Flow<UserPreference> = userPreferencesDataStore.current

    override suspend fun saveName(name: String) {
        userPreferencesDataStore.saveName(name)
    }

    override suspend fun savePhoto(photoUrl: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveTheme(theme: AppTheme) {
        userPreferencesDataStore.saveTheme(theme)
    }
}