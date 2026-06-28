package com.neptum.todolistapp.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.neptum.todolistapp.ui.Screen
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = koinViewModel()
) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val state = viewModel.state.collectAsState()
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(state.value) {
        when (val currentState = state.value) {
            is LoginState.Success -> {
                navController.navigate(Screen.Home.route)
            }
            is LoginState.Error -> {
                snackbarHostState.showSnackbar(currentState.message)
                viewModel.resetState()
            }
            else -> {}
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(text = "TO DO APP")
            Spacer(
                modifier = Modifier.height(48.dp)
            )
            OutlinedTextField(
                value = login,
                onValueChange = { login = it },
                label = { Text("LOGIN") }
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("PASSWORD") }
            )
            Spacer(
                modifier = Modifier.height(24.dp)
            )
            Button(
                onClick = { viewModel.logIn(login, password) }
            ) {
                Text(text = "Login")
            }
            Spacer(
                modifier = Modifier.height(24.dp)
            )
            Button(
                onClick = { navController.navigate(Screen.SignUp.route)}
            ) {
                Text(text = "Novo Usuário")
            }
            Spacer(
                modifier = Modifier.height(24.dp)
            )
            Button(
                onClick = { navController.navigate(Screen.RecoveredPassword.route)}
            ) {
                Text(text = "Esqueci a senha")
            }
        }
    }

}