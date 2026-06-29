package com.neptum.todolistapp.domain.usecase.userpreference

import com.neptum.todolistapp.data.datastore.AppTheme
import com.neptum.todolistapp.repository.UserPreferenceRepository

class InsertThemeUserPreferenceUseCase(
    private val userPreferenceRepository: UserPreferenceRepository
) {
    suspend operator fun invoke(theme: AppTheme) {
        userPreferenceRepository.saveTheme(theme)
    }
}