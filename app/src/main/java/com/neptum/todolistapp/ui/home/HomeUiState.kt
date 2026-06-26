package com.neptum.todolistapp.ui.home

import com.neptum.todolistapp.domain.model.Task

data class HomeUiState(
    val tasks: List<Task> = emptyList()
)