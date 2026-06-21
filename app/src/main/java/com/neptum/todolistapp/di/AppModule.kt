package com.neptum.todolistapp.di

import com.neptum.todolistapp.data.datasource.FireauthDataSource
import com.neptum.todolistapp.data.datasource.FirebaseDataSource
import com.neptum.todolistapp.data.repository.FireauthUserRepository
import com.neptum.todolistapp.domain.usecase.CreateUserUseCase
import com.neptum.todolistapp.domain.usecase.LogInUseCase
import com.neptum.todolistapp.repository.UserRepository
import com.neptum.todolistapp.ui.login.LoginViewModel
import com.neptum.todolistapp.ui.signup.SignUpViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        FirebaseDataSource()
    }

    single {
        FireauthDataSource()
    }

    single<UserRepository> {
        FireauthUserRepository(get())
    }

    factory {
        CreateUserUseCase(get())
    }

    factory {
        LogInUseCase(get())
    }

    viewModel {
        SignUpViewModel(get())
    }

    viewModel {
        LoginViewModel(get())
    }

}