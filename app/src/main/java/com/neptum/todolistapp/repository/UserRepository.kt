package com.neptum.todolistapp.repository

import com.neptum.todolistapp.domain.model.User

interface UserRepository {
    fun createUser(user: User)
}