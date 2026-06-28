package com.neptum.todolistapp.di

import com.neptum.todolistapp.data.datasource.FireauthDataSource
import com.neptum.todolistapp.data.datasource.FirebaseDataSource
import com.neptum.todolistapp.data.repository.FireauthUserRepository
import com.neptum.todolistapp.data.repository.FirebaseTaskRepositoryImpl
import com.neptum.todolistapp.data.repository.SessionRepository
import com.neptum.todolistapp.domain.usecase.LogInUseCase
import com.neptum.todolistapp.domain.usecase.LogOutUseCase
import com.neptum.todolistapp.domain.usecase.task.DeleteTaskUseCase
import com.neptum.todolistapp.domain.usecase.task.GetTaskUseCase
import com.neptum.todolistapp.domain.usecase.task.InsertTaskUseCase
import com.neptum.todolistapp.domain.usecase.task.UpdateTaskUseCase
import com.neptum.todolistapp.domain.usecase.user.CreateUserUseCase
import com.neptum.todolistapp.domain.usecase.user.RecoverUserPasswordUseCase
import com.neptum.todolistapp.repository.TaskRepository
import com.neptum.todolistapp.repository.UserRepository
import com.neptum.todolistapp.ui.home.HomeViewModel
import com.neptum.todolistapp.ui.login.LoginViewModel
import com.neptum.todolistapp.ui.recoverPassword.RecoverPasswordViewModel
import com.neptum.todolistapp.ui.session.SessionViewModel
import com.neptum.todolistapp.ui.signup.SignUpViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    singleOf(::FirebaseDataSource)
    singleOf(::FireauthDataSource)

    single<UserRepository> {
        FireauthUserRepository(get(), get())
    }

    single<TaskRepository> {
        FirebaseTaskRepositoryImpl(
            get(),
            get<FireauthDataSource>().auth
        )
    }

    single<SessionRepository> {
        SessionRepository(
            get<FireauthDataSource>().auth
        )
    }

    factoryOf(::CreateUserUseCase)
    factoryOf(::LogInUseCase)
    factoryOf(::LogOutUseCase)
    factoryOf(::InsertTaskUseCase)
    factoryOf(::GetTaskUseCase)
    factoryOf(::UpdateTaskUseCase)
    factoryOf(::DeleteTaskUseCase)
    factoryOf(::RecoverUserPasswordUseCase)

    factory {
        UpdateTaskUseCase(get())
    }

    factory {
        DeleteTaskUseCase(get())
    }

    factory {
        RecoverUserPasswordUseCase(get())
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

    viewModel {
        RecoverPasswordViewModel(get())
    }

    viewModel {
        SessionViewModel(get())
    }

}