package com.neptum.todolistapp.data.datasource

import com.google.firebase.auth.FirebaseAuth

class FireauthDataSource {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
}