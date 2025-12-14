package com.example.agroverdaspados.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agroverdaspados.ui.theme.screens.LoginScreen
import com.example.agroverdaspados.ui.theme.screens.RegisterScreen
import com.example.agroverdaspados.viewmodel.CarritoViewModel

// AppNavigation administra el flujo principal de la aplicación
@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val carritoViewModel: CarritoViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        // screen login
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate("register")
                }
            )
        }
        // screen registro
        composable("register") {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.popBackStack()
                }
            )
        }

        // screen principal (contiene los demas screens en 1)
        composable("main") {
            MainScreen(
                carritoViewModel = carritoViewModel,
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("main") { inclusive = true }
                    }
                }
            )
        }
    }
}
