package com.neptum.todolistapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neptum.todolistapp.domain.usecase.LogInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val logInUseCase: LogInUseCase
): ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state = _state.asStateFlow()

    fun logIn(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _state.value = LoginState.Loading
            val authResult = logInUseCase(email, password)

            authResult
                .onSuccess {
                    _state.value = LoginState.Success
                }
                .onFailure {
                    _state.value =
                        LoginState.Error(
                            it.message ?: "Falha no login"
                        )
                }
        }

    }

    fun resetState() {
        _state.value = LoginState.Idle
    }
}