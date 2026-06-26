package com.neptum.todolistapp.di

import com.neptum.todolistapp.data.datasource.FireauthDataSource
import com.neptum.todolistapp.data.datasource.FirebaseDataSource
import com.neptum.todolistapp.data.repository.FireauthUserRepository
import com.neptum.todolistapp.data.repository.FirebaseTaskRepositoryImpl
import com.neptum.todolistapp.domain.usecase.LogInUseCase
import com.neptum.todolistapp.domain.usecase.LogOutUseCase
import com.neptum.todolistapp.domain.usecase.task.GetTaskUseCase
import com.neptum.todolistapp.domain.usecase.task.InsertTaskUseCase
import com.neptum.todolistapp.domain.usecase.task.UpdateTaskUseCase
import com.neptum.todolistapp.domain.usecase.task.DeleteTaskUseCase
import com.neptum.todolistapp.domain.usecase.user.CreateUserUseCase
import com.neptum.todolistapp.repository.TaskRepository
import com.neptum.todolistapp.repository.UserRepository
import com.neptum.todolistapp.ui.home.HomeViewModel
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
        FireauthUserRepository(get(), get())
    }

    single<TaskRepository> {
        FirebaseTaskRepositoryImpl(
            get(),
            get<FireauthDataSource>().auth
        )
    }

    factory {
        CreateUserUseCase(get())
    }

    factory {
        LogInUseCase(get())
    }

    factory {
        LogOutUseCase(get())
    }

    factory {
        InsertTaskUseCase(get())
    }

    factory {
        GetTaskUseCase(get())
    }

    factory {
        UpdateTaskUseCase(get())
    }

    factory {
        DeleteTaskUseCase(get())
    }

    viewModel {
        SignUpViewModel(get())
    }

    viewModel {
        LoginViewModel(get())
    }

    viewModel {
        HomeViewModel(
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

}