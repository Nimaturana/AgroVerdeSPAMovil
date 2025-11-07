package com.example.agroverdaspados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.agroverdaspados.data.local.UserDataStore
import com.example.agroverdaspados.utils.ValidationUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val userDataStore = UserDataStore(application)

    // Campos del formulario
    private val _nombre = MutableStateFlow("")
    val nombre = _nombre.asStateFlow()

    private val _correo = MutableStateFlow("")
    val correo = _correo.asStateFlow()

    private val _clave = MutableStateFlow("")
    val clave = _clave.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    // actualizacion de campos
    fun onNombreChanged(value: String) {
        _nombre.value = value
    }

    fun onCorreoChanged(value: String) {
        _correo.value = value
    }

    fun onClaveChanged(value: String) {
        _clave.value = value
    }

    // validacion usando ValidationUtils
    fun validarFormulario(): Boolean {

        return when {
            !ValidationUtils.isNotEmpty(nombre.value, correo.value, clave.value) -> {
                _errorMessage.value = "Completa todos los campos"
                false
            }

            !ValidationUtils.isValidEmail(correo.value) -> {
                _errorMessage.value = "Correo electrónico inválido"
                false
            }

            !ValidationUtils.isValidPassword(clave.value) -> {
                _errorMessage.value = "La contraseña debe tener al menos 4 caracteres"
                false
            }

            else -> {
                _errorMessage.value = ""
                true
            }
        }
    }

    // guarda usuario en DataStore
    fun registrarUsuario(onSuccess: () -> Unit) {
        if (!validarFormulario()) return

        viewModelScope.launch {
            userDataStore.saveUser(
                nombre.value,
                correo.value,
                clave.value
            )
            onSuccess()
        }
    }
}
