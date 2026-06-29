package com.neptum.todolistapp.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neptum.todolistapp.ui.home.HomeScreen
import com.neptum.todolistapp.ui.login.LoginScreen
import com.neptum.todolistapp.ui.preferences.UserPreferenceScreen
import com.neptum.todolistapp.ui.recoverPassword.RecoverPassword
import com.neptum.todolistapp.ui.session.SplashScreen
import com.neptum.todolistapp.ui.signup.SignUpScreen

@Composable
fun AppNavigation(
    innerPadding: PaddingValues
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(Screen.Login.route) {
            LoginScreen(
                navController,
            )
        }

        composable(Screen.SignUp.route) {
            SignUpScreen(
                navController,
                paddingValues = innerPadding
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                navController,
                paddingValues = innerPadding
            )
        }

        composable(Screen.RecoveredPassword.route) {
            RecoverPassword(
                navController,
                paddingValues = innerPadding
            )
        }

        composable(Screen.Splash.route) {
            SplashScreen(
                navController
            )
        }

        composable(Screen.Preferences.route) {
            UserPreferenceScreen(
                navController,
                paddingValues = innerPadding,
            )
        }
    }
}