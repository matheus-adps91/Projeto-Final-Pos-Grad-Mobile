package com.neptum.todolistapp.data.dto

data class TaskFirebaseDto(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val finished: Boolean = false,
)