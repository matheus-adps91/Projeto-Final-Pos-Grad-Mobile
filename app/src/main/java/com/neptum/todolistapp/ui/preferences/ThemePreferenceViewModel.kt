package com.neptum.todolistapp.ui.preferences

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neptum.todolistapp.data.datastore.AppTheme
import com.neptum.todolistapp.data.datastore.UserPreference
import com.neptum.todolistapp.domain.usecase.userpreference.GetThemeUserPreferenceUseCase
import com.neptum.todolistapp.domain.usecase.userpreference.InsertThemeUserPreferenceUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ThemePreferenceViewModel(
    private val themeUserPreferenceUseCase: InsertThemeUserPreferenceUseCase,
    private val getThemeUserPreferenceUseCase: GetThemeUserPreferenceUseCase
) : ViewModel() {

    val theme: StateFlow<UserPreference> = getThemeUserPreferenceUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = UserPreference()
        )

    fun saveTheme(theme: AppTheme) {
        viewModelScope.launch {
            themeUserPreferenceUseCase(theme)
        }
    }
}