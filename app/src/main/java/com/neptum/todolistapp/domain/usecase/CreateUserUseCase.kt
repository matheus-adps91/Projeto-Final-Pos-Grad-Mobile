package com.neptum.todolistapp.domain.usecase

import com.neptum.todolistapp.domain.model.User
import com.neptum.todolistapp.repository.UserRepository

class CreateUserUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(user: User) {
        repository.createUser(user)
    }
}