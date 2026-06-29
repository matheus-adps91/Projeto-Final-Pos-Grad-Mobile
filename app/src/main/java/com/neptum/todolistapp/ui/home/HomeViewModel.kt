package com.neptum.todolistapp.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neptum.todolistapp.domain.model.Task
import com.neptum.todolistapp.domain.usecase.task.DeleteTaskUseCase
import com.neptum.todolistapp.domain.usecase.task.GetTaskUseCase
import com.neptum.todolistapp.domain.usecase.task.InsertTaskUseCase
import com.neptum.todolistapp.domain.usecase.task.UpdateTaskUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID

class HomeViewModel(
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

    fun saveTask(
        id: String?,
        title: String,
        description: String,
        finished: Boolean = false,
        finishDateTime: LocalDateTime
    ) {
        viewModelScope.launch {
            if (id == null) {
                val task = Task(
                    id = UUID.randomUUID().toString(),
                    title = title,
                    description = description,
                    finished = finished,
                    finishDateTime = finishDateTime
                )
                insertTaskUseCase(task)
            } else {
                val task = Task(
                    id = id,
                    title = title,
                    description = description,
                    finished = finished,
                    finishDateTime = finishDateTime
                )
                updateTaskUseCase(task)
            }
            _events.emit(HomeEvent.TaskSaved)
            setEditingTask(null)
        }
    }

    fun setEditingTask(task: Task?) {
        state = state.copy(editingTask = task)
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

}

sealed interface HomeEvent {
    object TaskSaved : HomeEvent
}