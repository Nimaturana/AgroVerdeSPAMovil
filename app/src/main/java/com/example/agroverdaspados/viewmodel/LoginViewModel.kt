package com.example.agroverdaspados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.agroverdaspados.data.local.UserDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val userDataStore = UserDataStore(application)

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun onEmailChanged(value: String) {
        _email.value = value
    }

    fun onPasswordChanged(value: String) {
        _password.value = value
    }

    fun login(onSuccess: () -> Unit) {
        viewModelScope.launch {

            // lee datos guardados en DataStore
            val savedEmail = userDataStore.getUserEmail().first()
            val savedPassword = userDataStore.getUserPassword().first()

            when {

                _email.value.isEmpty() || _password.value.isEmpty() -> {
                    _errorMessage.value = "Completa todos los campos"
                }

                !_email.value.contains("@") -> {
                    _errorMessage.value = "El correo electrónico debe contener '@'"
                }

                savedEmail.isNullOrEmpty() || savedPassword.isNullOrEmpty() -> {
                    _errorMessage.value = "No existe un usuario registrado"
                }

                // compara email real guardada en DataStore
                _email.value != savedEmail -> {
                    _errorMessage.value = "Email y/o Contraseña incorrecta, intentar nuevamnete"
                }

                // compara contraseña real guardada en DataStore
                _password.value != savedPassword -> {
                    _errorMessage.value = "Email y/o Contraseña incorrecta, intentar nuevamnete"
                }

                else -> {
                    _errorMessage.value = ""
                    onSuccess()
                }
            }
        }
    }
}
