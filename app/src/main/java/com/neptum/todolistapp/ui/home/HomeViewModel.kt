package com.neptum.todolistapp.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neptum.todolistapp.domain.model.Task
import com.neptum.todolistapp.domain.usecase.LogOutUseCase
import com.neptum.todolistapp.domain.usecase.task.GetTaskUseCase
import com.neptum.todolistapp.domain.usecase.task.InsertTaskUseCase
import com.neptum.todolistapp.domain.usecase.task.UpdateTaskUseCase
import com.neptum.todolistapp.domain.usecase.task.DeleteTaskUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.UUID

class HomeViewModel(
    private val logOutUseCase: LogOutUseCase,
    private val insertTaskUseCase: InsertTaskUseCase,
    private val getTaskUseCase: GetTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {

    private val _events = MutableSharedFlow<HomeEvent>()
    val events = _events.asSharedFlow()
    var state by mutableStateOf(HomeUiState())
        private set


    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            getTaskUseCase().collect { tasks ->
                state = state.copy(tasks = tasks)
            }
        }
    }

    fun insertTask(
        title: String,
        description: String,
    ) {
        viewModelScope.launch {
            val task = Task(
                id = UUID.randomUUID().toString(),
                title = title,
                description = description,
                finished = false
            )
            insertTaskUseCase(task)
            _events.emit(HomeEvent.TaskSaved)
        }
    }

    fun toggleTaskStatus(task: Task) {
        viewModelScope.launch {
            val updatedTask = task.copy(finished = !task.finished)
            updateTaskUseCase(updatedTask)
        }
    }

    fun deleteTask(taskId: String) {
        viewModelScope.launch {
            deleteTaskUseCase(taskId)
        }
    }

    fun logOut() {
        viewModelScope.launch {
            logOutUseCase().onSuccess {
                _events.emit(HomeEvent.LogoutSuccess)
            }
        }
    }
}

sealed interface HomeEvent {
    object LogoutSuccess : HomeEvent
    object TaskSaved : HomeEvent
}