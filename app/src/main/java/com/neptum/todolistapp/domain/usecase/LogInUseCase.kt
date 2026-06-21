package com.neptum.todolistapp.domain.usecase

import com.google.firebase.auth.FirebaseUser
import com.neptum.todolistapp.repository.UserRepository

class LogInUseCase(
    private val repository: UserRepository,
) {
    suspend operator fun invoke(
        username: String,
        password: String
    ): Result<FirebaseUser> {
        return repository.logIn(username, password)
    }
}