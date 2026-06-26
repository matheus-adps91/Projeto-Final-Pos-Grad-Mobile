package com.neptum.todolistapp.domain.usecase.task

import com.neptum.todolistapp.domain.model.Task
import com.neptum.todolistapp.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTaskUseCase(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(): Flow<List<Task>> {
        return taskRepository.getTasks()
    }
}