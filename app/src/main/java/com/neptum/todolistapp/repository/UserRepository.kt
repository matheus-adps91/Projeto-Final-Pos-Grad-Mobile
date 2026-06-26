package com.neptum.todolistapp.repository

import com.google.firebase.auth.FirebaseUser
import com.neptum.todolistapp.domain.model.User

interface UserRepository {
    suspend fun createUser(user: User): Result<FirebaseUser>

    suspend fun logIn(email: String, password: String): Result<FirebaseUser>

    suspend fun logOut(): Result<Unit>
}