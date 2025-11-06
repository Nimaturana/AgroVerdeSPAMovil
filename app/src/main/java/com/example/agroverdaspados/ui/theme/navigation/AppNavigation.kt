package com.example.agroverdaspados.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agroverdaspados.ui.theme.screens.HomeScreen
import com.example.agroverdaspados.ui.theme.screens.LoginScreen
import com.example.agroverdaspados.ui.theme.screens.ProfileScreen
import com.example.agroverdaspados.ui.theme.screens.RegisterScreen

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {

        // 🔹 pantalla Login
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("home") },
                onNavigateToRegister = { navController.navigate("register") }
            )
        }

        //  pantalla Registro
        composable("register") {
            RegisterScreen(
                onRegisterSuccess = { navController.navigate("login") } // vuelve al login al registrarse
            )
        }

        //  pantalla Home
        composable("home") {
            HomeScreen(onProfileClick = { navController.navigate("profile") })
        }

        //  pantalla Perfil
        composable("profile") {
            ProfileScreen(onBackClick = { navController.popBackStack() })
        }
    }
}
