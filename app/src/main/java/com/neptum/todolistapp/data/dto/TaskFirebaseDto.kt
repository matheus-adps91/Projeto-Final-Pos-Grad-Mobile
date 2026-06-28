package com.neptum.todolistapp.data.dto

import com.google.firebase.Timestamp

data class TaskFirebaseDto(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val finished: Boolean = false,
    val finishDateTime: Timestamp? = null
)