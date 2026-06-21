package com.neptum.todolistapp.ui.login

sealed interface LoginState {
    object Idle : LoginState
    object Loading : LoginState
    object Success : LoginState
    data class Error(val message: String) : LoginState
}