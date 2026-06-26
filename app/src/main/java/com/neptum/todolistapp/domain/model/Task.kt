package com.neptum.todolistapp.domain.model

data class Task(
    val id: String,
    val title: String,
    val description: String,
    val finished: Boolean
)