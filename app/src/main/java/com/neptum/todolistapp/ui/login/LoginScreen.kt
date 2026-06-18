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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.neptum.todolistapp.ui.Screen

@Composable
fun LoginScreen(
    navController: NavController,
    paddingValues: PaddingValues
) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Text(text = "TO DO APP")
        Spacer(
            modifier = Modifier.height(48.dp)
        )
        OutlinedTextField(
            value = "",
            onValueChange = { login = it },
            label = { Text("LOGIN") }
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        OutlinedTextField(
            value = "",
            onValueChange = { password = it },
            label = { Text("PASSWORD") }
        )
        Spacer(
            modifier = Modifier.height(24.dp)
        )
        Button(
            onClick = { }
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
    }
}