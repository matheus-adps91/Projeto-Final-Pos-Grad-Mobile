package com.neptum.todolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.neptum.todolistapp.ui.AppNavigation
import com.neptum.todolistapp.ui.preferences.ThemePreferenceViewModel
import com.neptum.todolistapp.ui.theme.ToDoListAppTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val themePreferenceViewModel: ThemePreferenceViewModel = koinViewModel()

            val theme = themePreferenceViewModel.theme.collectAsState()

            ToDoListAppTheme(
                appTheme = theme.value.theme
            ) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(innerPadding)
                }
            }
        }
    }
}

