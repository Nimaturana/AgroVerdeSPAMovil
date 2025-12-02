package com.example.agroverdaspados.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agroverdaspados.ui.theme.screens.HomeScreen
import com.example.agroverdaspados.ui.theme.screens.LoginScreen
import com.example.agroverdaspados.ui.theme.screens.ProductosScreen
import com.example.agroverdaspados.ui.theme.screens.ProfileScreen
import com.example.agroverdaspados.ui.theme.screens.RegisterScreen

    // appnavigation administra todas las pantallas y se definen cual traer
@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {

        //  pantalla Login
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("home") },
                onNavigateToRegister = { navController.navigate("register") }
            )
        }

        //  pantalla registro
        composable("register") {
            RegisterScreen(
                onRegisterSuccess = { navController.navigate("login") } // vuelve al login al registrarse
            )
        }

        //  pantalla home
        composable("home") {
            HomeScreen(
                onProfileClick = { navController.navigate("profile") },
                onProductosClick = { navController.navigate("productos") },
                onLogoutClick = {
                    navController.navigate("login") {
                        // Limpia todo el back stack para que no se pueda volver atrás después del logout
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        //  pantalla perfil
        composable("profile") {
            ProfileScreen(onBackClick = { navController.popBackStack() })


        }

        composable("productos") {
            ProductosScreen()
        }

    }
}
