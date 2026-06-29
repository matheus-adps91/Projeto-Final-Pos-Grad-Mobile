package com.neptum.todolistapp.data.datastore

data class UserPreference(
    val name: String,
    val photoUrl: String?,
    val theme: AppTheme
)