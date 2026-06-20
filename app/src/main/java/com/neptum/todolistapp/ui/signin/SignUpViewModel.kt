package com.neptum.todolistapp.ui.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neptum.todolistapp.domain.model.User
import com.neptum.todolistapp.domain.usecase.CreateUserUseCase
import kotlinx.coroutines.launch
import java.util.UUID

class SignUpViewModel (
    private val createUserUseCase: CreateUserUseCase
): ViewModel(){
    fun createUser(
        name: String,
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            val newUser = User(
                id = UUID.randomUUID().toString(),
                name = name,
                email = email,
                password = password
            )
            createUserUseCase(newUser)
        }

    }
}
