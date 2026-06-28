package com.neptum.todolistapp.ui.session

import androidx.lifecycle.ViewModel
import com.neptum.todolistapp.data.repository.SessionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SessionViewModel(
    private val sessionRepository: SessionRepository
): ViewModel() {
    private val _logged = MutableStateFlow(
        sessionRepository.isLogged()
    )
    val logged = _logged.asStateFlow()

    fun logout() {
        sessionRepository.logout()
        _logged.value = false
    }

    fun refreshSession() {
        _logged.value = sessionRepository.isLogged()
    }
}