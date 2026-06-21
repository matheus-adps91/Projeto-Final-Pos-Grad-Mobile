package com.neptum.todolistapp.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neptum.todolistapp.domain.model.User
import com.neptum.todolistapp.domain.usecase.CreateUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class SignUpViewModel (
    private val createUserUseCase: CreateUserUseCase
): ViewModel(){

    private val _state = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val state = _state.asStateFlow()

    fun createUser(
        name: String,
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _state.value = SignUpState.Loading

            val newUser = User(
                id = UUID.randomUUID().toString(),
                name = name,
                email = email,
                password = password
            )
            val result = createUserUseCase(newUser)

            result
                .onSuccess {
                    _state.value = SignUpState.Success
                }
                .onFailure {
                    _state.value = SignUpState.Error(
                        it.message ?: "Erro desconhecido"
                    )
                }
        }

    }
}
