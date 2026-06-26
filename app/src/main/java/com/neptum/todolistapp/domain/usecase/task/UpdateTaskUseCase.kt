package com.neptum.todolistapp.domain.usecase.task

import com.neptum.todolistapp.domain.model.Task
import com.neptum.todolistapp.repository.TaskRepository

class UpdateTaskUseCase(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task) {
        repository.updateTask(task)
    }
}