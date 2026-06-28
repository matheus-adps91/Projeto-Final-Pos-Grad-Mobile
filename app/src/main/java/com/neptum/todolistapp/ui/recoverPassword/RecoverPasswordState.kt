package com.neptum.todolistapp.ui.recoverPassword

sealed interface RecoverPasswordState {

    object Idle : RecoverPasswordState
    object Success : RecoverPasswordState
    data class Error(
        val message: String
    ) : RecoverPasswordState
}