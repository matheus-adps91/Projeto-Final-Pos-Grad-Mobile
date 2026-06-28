package com.neptum.todolistapp.ui

import androidx.compose.animation.AnimatedContentScope
import androidx.navigation.NavBackStackEntry

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object SignUp : Screen("signup")

    object Home : Screen("home")

    object RecoveredPassword : Screen("recoveredPassword")

    object Splash : Screen("splash")
}