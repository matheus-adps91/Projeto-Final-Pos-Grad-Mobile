package com.neptum.todolistapp.repository

import com.neptum.todolistapp.data.datastore.AppTheme
import com.neptum.todolistapp.data.datastore.UserPreference
import kotlinx.coroutines.flow.Flow

interface UserPreferenceRepository {

    val preference: Flow<UserPreference>

    suspend fun saveName(name: String)

    suspend fun savePhoto(photoUrl: String)

    suspend fun saveTheme(theme: AppTheme)
}