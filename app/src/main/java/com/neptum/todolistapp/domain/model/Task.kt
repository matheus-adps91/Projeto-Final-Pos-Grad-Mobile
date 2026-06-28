package com.neptum.todolistapp.domain.model

import java.time.LocalDateTime

data class Task(
    val id: String,
    val title: String,
    val description: String,
    val finished: Boolean,
    val finishDateTime: LocalDateTime
)