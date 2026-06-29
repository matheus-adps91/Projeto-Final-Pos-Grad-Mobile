package com.neptum.todolistapp.domain.usecase.userpreference

import com.neptum.todolistapp.repository.UserPreferenceRepository

class InsertNameUserPrefenceUseCase(
    private val userPreferenceRepository: UserPreferenceRepository
) {
    suspend operator fun invoke(name: String) {
        userPreferenceRepository.saveName(name)
    }
}