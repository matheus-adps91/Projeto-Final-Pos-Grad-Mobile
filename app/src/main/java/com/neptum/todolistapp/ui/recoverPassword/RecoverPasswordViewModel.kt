package com.neptum.todolistapp.ui.recoverPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neptum.todolistapp.domain.usecase.user.RecoverUserPasswordUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecoverPasswordViewModel(
    private val recoverUserPasswordUseCase: RecoverUserPasswordUseCase
): ViewModel() {

    private val _state = MutableStateFlow<RecoverPasswordState>(
        RecoverPasswordState.Idle)
    val state = _state.asStateFlow()
    fun recoverPassword(email: String) {
        viewModelScope.launch() {
            recoverUserPasswordUseCase(email)
                .onSuccess {
                    _state.value = RecoverPasswordState.Success
                }
                .onFailure {
                    _state.value = RecoverPasswordState
                        .Error(it.message ?: "Erro desconhecido")
                }
        }
    }
}