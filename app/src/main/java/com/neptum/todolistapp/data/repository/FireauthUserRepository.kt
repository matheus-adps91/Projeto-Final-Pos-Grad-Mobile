package com.neptum.todolistapp.data.repository

import com.neptum.todolistapp.data.datasource.FireauthDataSource
import com.neptum.todolistapp.domain.model.User
import com.neptum.todolistapp.repository.UserRepository

class FireauthUserRepository(
    private val fireAuthDataSource: FireauthDataSource
): UserRepository {
    override fun createUser(user: User) {
        fireAuthDataSource
            .auth
            .createUserWithEmailAndPassword(user.email, user.password)
    }

}