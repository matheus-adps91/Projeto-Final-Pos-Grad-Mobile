package com.neptum.todolistapp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.neptum.todolistapp.ui.Screen
import com.neptum.todolistapp.ui.components.TaskFormCard
import com.neptum.todolistapp.ui.preferences.UserPreferenceScreen
import com.neptum.todolistapp.ui.screen.TaskList
import com.neptum.todolistapp.ui.session.SessionViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    paddingValues: PaddingValues,
    homeViewModel: HomeViewModel = koinViewModel(),
    sessionViewModel: SessionViewModel = koinViewModel()
) {
    val events = homeViewModel.events
    val state = homeViewModel.state

    var showTaskCard by remember { mutableStateOf(false) }
    val loggedState = sessionViewModel.logged.collectAsState()

    LaunchedEffect(loggedState.value) {
        if (!loggedState.value) {
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Home.route) { inclusive = true }
            }
        }
    }

    LaunchedEffect(Unit) {
        events.collect { event ->
            when (event) {
                is HomeEvent.TaskSaved -> {
                    // Opcional: Mostrar uma mensagem de sucesso ou log
                }
            }
        }
    }
    Scaffold(
        modifier = Modifier.padding(paddingValues),
        topBar = {
            TopAppBar(
                title = { Text("To Do List") },
                actions = {
                    IconButton(
                        onClick = { navController.navigate(Screen.Preferences.route) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Preferências"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showTaskCard = true }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Adicionar Tarefa")
            }
        }
    ) { innerPadding ->
        if (showTaskCard || state.editingTask != null) {
            TaskFormCard(
                task = state.editingTask,
                onDismiss = {
                    showTaskCard = false
                    homeViewModel.setEditingTask(null)
                }
            )
        }

        Box(modifier = Modifier.padding(innerPadding)) {
            TaskList(
                state,
                onEdit = { homeViewModel.setEditingTask(it) },
                onDelete = { homeViewModel.deleteTask(it.id) },
                onToggleStatus = homeViewModel::toggleTaskStatus
            )
        }
    }
}