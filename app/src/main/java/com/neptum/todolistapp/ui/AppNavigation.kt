package com.neptum.todolistapp.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neptum.todolistapp.ui.login.LoginScreen
import com.neptum.todolistapp.ui.signin.SignUpScreen

@Composable
fun AppNavigation(
    innerPadding: PaddingValues
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {

        composable(Screen.Login.route) {
            LoginScreen(
                navController,
                paddingValues = innerPadding
            )
        }

        composable(Screen.SignUp.route) {
            SignUpScreen(
                navController,
                paddingValues = innerPadding
            )
        }
    }
}