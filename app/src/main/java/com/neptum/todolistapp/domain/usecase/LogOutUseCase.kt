package com.neptum.todolistapp.domain.usecase

import com.neptum.todolistapp.repository.UserRepository

class LogOutUseCase(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(): Result<Unit> {
        return repository.logOut()
    }
}