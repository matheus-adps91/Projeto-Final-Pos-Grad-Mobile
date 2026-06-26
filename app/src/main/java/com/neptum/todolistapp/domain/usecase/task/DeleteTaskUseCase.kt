package com.neptum.todolistapp.domain.usecase.task

import com.neptum.todolistapp.repository.TaskRepository

class DeleteTaskUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(taskId: String) {
        repository.deleteTask(taskId)
    }
}