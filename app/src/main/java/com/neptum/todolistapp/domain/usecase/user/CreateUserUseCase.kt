package com.neptum.todolistapp.domain.usecase.user

import com.google.firebase.auth.FirebaseUser
import com.neptum.todolistapp.domain.model.User
import com.neptum.todolistapp.repository.UserRepository

class CreateUserUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        user: User
    ): Result<FirebaseUser> {
        return repository.createUser(user)
    }
}