package com.neptum.todolistapp.ui.preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neptum.todolistapp.data.datastore.UserPreference
import com.neptum.todolistapp.domain.usecase.LogOutUseCase
import com.neptum.todolistapp.domain.usecase.userpreference.GetUserPreferenceUseCase
import com.neptum.todolistapp.domain.usecase.userpreference.InsertNameUserPrefenceUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserPreferenceViewModel(
    private val getUserPreferenceUseCase: GetUserPreferenceUseCase,
    private val insertNameUserPrefenceUseCase: InsertNameUserPrefenceUseCase,
    private val logOutUseCase: LogOutUseCase
): ViewModel() {

    private val _events = MutableSharedFlow<PreferencesEvent>()
    val events = _events.asSharedFlow()

    val preferences: StateFlow<UserPreference?> = getUserPreferenceUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun saveName(name: String) {
        viewModelScope.launch {
            insertNameUserPrefenceUseCase(name)
        }
    }

    fun logOut() {
        viewModelScope.launch {
            logOutUseCase().onSuccess {
                _events.emit(PreferencesEvent.LogoutSuccess)
            }
        }
    }
}

sealed interface PreferencesEvent {
    object LogoutSuccess : PreferencesEvent
}