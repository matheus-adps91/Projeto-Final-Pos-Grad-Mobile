package com.neptum.todolistapp.domain.usecase.userpreference

import com.neptum.todolistapp.data.datastore.UserPreference
import com.neptum.todolistapp.repository.UserPreferenceRepository
import kotlinx.coroutines.flow.Flow

class GetThemeUserPreferenceUseCase(
    private val userPreferenceRepository: UserPreferenceRepository
) {
    operator fun invoke(): Flow<UserPreference> {
        return userPreferenceRepository.preference
    }
}