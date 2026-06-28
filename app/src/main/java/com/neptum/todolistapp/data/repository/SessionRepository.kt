package com.neptum.todolistapp.data.repository

import com.google.firebase.auth.FirebaseAuth

class SessionRepository(
    private val auth: FirebaseAuth,
) {
    fun currentUser() = auth.currentUser

    fun isLogged() = auth.currentUser != null

    fun logout() = auth.signOut()
}