package com.neptum.todolistapp.data.datastore

data class UserPreference(
    val name: String = "Desconhecido",
    val photoUrl: String? = null,
    val theme: AppTheme = AppTheme.SYSTEM
)