package com.example.agroverdaspados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.agroverdaspados.data.local.UserSessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val sessionManager = UserSessionManager(application.applicationContext)

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
        when {

            _email.value.isEmpty() || _password.value.isEmpty() -> {
                _errorMessage.value = "Completa todos los campos"
            }
            !_email.value.contains("@") -> {
                _errorMessage.value = "El correo electrónico debe contener '@'"
            }

            else -> {
                _errorMessage.value = ""
                // Guarda el email en DataStore y luego se pone en el perfil
                viewModelScope.launch {
                    sessionManager.saveUserEmail(_email.value)
                }
                onSuccess()
            }
        }
    }
}