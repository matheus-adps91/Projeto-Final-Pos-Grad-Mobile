package com.neptum.todolistapp.domain.usecase.user

import com.neptum.todolistapp.repository.UserRepository

class RecoverUserPasswordUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        email: String
    ): Result<Unit> {
        return repository.sendPasswordByEmail(email)
    }
}