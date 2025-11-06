package com.example.agroverdaspados.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.res.painterResource
import com.example.agroverdaspados.R
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.agroverdaspados.viewmodel.LoginViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.layout.ContentScale

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit, // ✅ nuevo parámetro agregado
    viewModel: LoginViewModel = viewModel()
) {
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    var passwordVisible by remember { mutableStateOf(false) }

    // Estructura principal de la pantalla: columna centrada
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo de la app
        Image(
            painter = painterResource(id = R.drawable.logoagro),
            contentDescription = "Logo AgroVerde SPA",
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .padding(bottom = 16.dp)
                .aspectRatio(426f / 100f)
        )

        // Título
        Text(
            text = "Iniciar Sesion",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 3.dp),
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de correo
        TextField(
            value = email,
            onValueChange = viewModel::onEmailChanged,
            label = { Text("Correo electrónico") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de contraseña con ojito
        TextField(
            value = password,
            onValueChange = viewModel::onPasswordChanged,
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            },
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para iniciar sesión
        Button(
            onClick = { viewModel.login(onLoginSuccess) },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(0.6f)
        ) {
            Text("Iniciar Sesión")
        }

        // 🔹 Botón para ir a registro
        TextButton(
            onClick = { onNavigateToRegister() },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("¿No tienes cuenta? Regístrate aquí")
        }

        // Mensaje de error
        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    MaterialTheme {

        //  agregamos ambos parámetros para evitar errores
        LoginScreen(
            onLoginSuccess = { /* Simulación */ },
            onNavigateToRegister = { /* Simulación */ }
        )
    }
}
