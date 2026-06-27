package com.neptum.todolistapp.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.neptum.todolistapp.data.datasource.FirebaseDataSource
import com.neptum.todolistapp.data.dto.TaskFirebaseDto
import com.neptum.todolistapp.data.mapper.toDomain
import com.neptum.todolistapp.data.mapper.toTaskFirebaseDto
import com.neptum.todolistapp.domain.model.Task
import com.neptum.todolistapp.repository.TaskRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirebaseTaskRepositoryImpl(
    private val dataSource: FirebaseDataSource,
    private val auth: FirebaseAuth,
): TaskRepository {

    override suspend fun createTask(task: Task) {
        val user = auth.currentUser ?: return
        val dto = task.copy().toTaskFirebaseDto()
        dataSource.insertUserTasksCollection(user.uid, dto)
    }

    override suspend fun updateTask(task: Task) {
        val user = auth.currentUser ?: return
        val dto = task.toTaskFirebaseDto()
        dataSource.insertUserTasksCollection(user.uid, dto)
    }

    override suspend fun deleteTask(taskId: String) {
        val user = auth.currentUser ?: return
        dataSource.deleteTask(user.uid, taskId)
    }

    override fun getTasks(): Flow<List<Task>> = callbackFlow {
        val user = auth.currentUser
        if (user == null) {
            trySend(emptyList())
            close()
            return@callbackFlow
        }

        val listener = dataSource.getUserTasksCollection(user.uid).addSnapshotListener { snapshots, _ ->
            if (snapshots == null) {
                trySend(emptyList())
                return@addSnapshotListener
            }

            val list = snapshots.documents.mapNotNull { document ->
                document
                    .toObject(TaskFirebaseDto::class.java)
                    ?.toDomain()
            }

            trySend(list)
        }

        awaitClose {
            listener.remove()
        }
    }
}