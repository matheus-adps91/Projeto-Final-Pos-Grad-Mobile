package com.neptum.todolistapp.repository

import com.neptum.todolistapp.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun createTask(task: Task)

    suspend fun updateTask(task: Task)

    suspend fun deleteTask(taskId: String)

    fun getTasks(): Flow<List<Task>>
}