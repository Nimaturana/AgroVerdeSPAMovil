package com.example.agroverdaspados.ui.theme.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.agroverdaspados.viewmodel.CarritoViewModel
import com.example.agroverdaspados.ui.theme.screens.*


// pantalla principal que contiene las demas screen en 1 (excepto login y register)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    carritoViewModel: CarritoViewModel,
    onLogout: () -> Unit
) {
    val navController = rememberNavController()


    // botones bajos
    Scaffold(
        bottomBar = {
            NavigationBar {
                // boton home

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("home") },
                    label = { Text("Home") },
                    icon = { Icon(Icons.Default.Home, contentDescription = null) }
                )

                // sboton productos
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("productos") },
                    label = { Text("Productos") },
                    icon = { Icon(Icons.Default.ShoppingBag, contentDescription = null) }
                )

                // boton carrito
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("carrito") },
                    label = { Text("Carrito") },
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = null) }
                )

                // boton profile
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("profile") },
                    label = { Text("Perfil") },
                    icon = { Icon(Icons.Default.Person, contentDescription = null) }
                )
            }
        }

        // panddingvalues evita errores en diferentes resoluciones de diferentes telefonos
        // evita que el contenido quede debajo de los botones principales de android
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {

            // screen home
            composable("home") {
                HomeScreen(
                    onProfileClick = { navController.navigate("profile") },
                    onProductosClick = { navController.navigate("productos") },
                    onLogoutClick = onLogout
                )
            }

            // screen productos
            composable("productos") {
                ProductosScreen(
                    navController = navController,
                    carritoViewModel = carritoViewModel
                )
            }

            // screen carrito
            composable("carrito") {
                CarritoScreen(
                    navController = navController,
                    carritoViewModel = carritoViewModel
                )
            }

            // screen profile
            composable("profile") {
                ProfileScreen(
                    onBackClick = { navController.popBackStack() },
                    onLogoutClick = onLogout
                )
            }

            // screen pedidoExito
            composable("pedido_exito") {
                PedidoExitoScreen(
                    navController = navController
                )
            }
        }
    }
}
