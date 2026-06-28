package com.neptum.todolistapp.data.repository

import com.google.firebase.auth.FirebaseUser
import com.neptum.todolistapp.data.datasource.FireauthDataSource
import com.neptum.todolistapp.data.datasource.FirebaseDataSource
import com.neptum.todolistapp.domain.model.User
import com.neptum.todolistapp.repository.UserRepository
import kotlinx.coroutines.tasks.await

class FireauthUserRepository(
    private val fireAuthDataSource: FireauthDataSource,
    private val firebaseDataSource: FirebaseDataSource,
): UserRepository {
    override suspend fun createUser(
        user: User
    ): Result<FirebaseUser> {
        return try {
            val authResult =
                fireAuthDataSource
                    .auth
                    .createUserWithEmailAndPassword(
                        user.email, user.password)
                    .await()

            val userCreated = authResult.user!!
            firebaseDataSource.createDocumentForNewUsers(userCreated).await()
            Result.success(userCreated)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logIn(
        email: String,
        password: String
    ): Result<FirebaseUser> {
        return try {
            val authResult =
                fireAuthDataSource
                    .auth
                    .signInWithEmailAndPassword(email, password)
                    .await()
            Result.success(authResult.user!!)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun logOut(): Result<Unit> {
        return try {
            fireAuthDataSource.auth.signOut()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun sendPasswordByEmail(
        email: String
    ): Result<Unit> {
        return try {
            fireAuthDataSource
                .auth
                .sendPasswordResetEmail(
                    email)
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}