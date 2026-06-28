package com.neptum.todolistapp.ui.recoverPassword

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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecoverPassword(
    navController: NavHostController,
    paddingValues: PaddingValues,
    viewModel: RecoverPasswordViewModel = koinViewModel()
){
    var login by remember { mutableStateOf("") }
    val state = viewModel.state.collectAsState()

    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.value) {
        when(state.value) {
            RecoverPasswordState.Success -> {
                snackBarHostState.showSnackbar(
                    message = "Email enviado com sucesso.Verifique sua caixa de entrada.",
                    duration = SnackbarDuration.Indefinite,
                    actionLabel = "Ok"
                )
                navController.navigate("login") {
                    popUpTo("recoverPassword") {
                        inclusive = true
                    }
                }
            }
            is RecoverPasswordState.Error -> {
                snackBarHostState.showSnackbar(
                    message = "Tente novamente mais tarde.",
                    duration = SnackbarDuration.Indefinite,
                    actionLabel = "Ok"
                )
            }
            else -> {}
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Esqueci a senha")
            Spacer(
                modifier = Modifier.height(24.dp)
            )
            OutlinedTextField(
                value = login,
                onValueChange = { login = it },
                label = { Text("Login") }
            )
            Spacer(
                modifier = Modifier.height(24.dp)
            )
            Button(
                onClick = { viewModel.recoverPassword(login) }
            ) {
                Text(text = "Recuperar")
            }
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Button(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text("Voltar")
            }
        }
    }
}