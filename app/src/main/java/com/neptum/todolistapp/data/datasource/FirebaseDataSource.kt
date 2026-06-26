package com.neptum.todolistapp.data.datasource

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.neptum.todolistapp.data.dto.TaskFirebaseDto
import kotlinx.coroutines.tasks.await

class FirebaseDataSource {
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun insertUserTasksCollection(uid: String, task: TaskFirebaseDto): Void? = firestore
        .collection("users")
        .document(uid)
        .collection("tasks")
        .document(task.id)
        .set(task)
        .await()

    fun getUserTasksCollection(uid: String) = firestore
        .collection("users")
        .document(uid)
        .collection("tasks")

    suspend fun deleteTask(uid: String, taskId: String): Void? = firestore
        .collection("users")
        .document(uid)
        .collection("tasks")
        .document(taskId)
        .delete()
        .await()

    fun createDocumentForNewUsers(userCreate: FirebaseUser) = firestore
        .collection("users")
        .document(userCreate.uid)
        .set(
            mapOf(
                "email" to userCreate.email,
            )
        )
}