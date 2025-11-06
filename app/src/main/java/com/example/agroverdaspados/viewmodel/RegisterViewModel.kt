package com.example.agroverdaspados.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RegisterViewModel : ViewModel() {

    // Campos del formulario
    private val _nombre = MutableStateFlow("")
    val nombre = _nombre.asStateFlow()

    private val _correo = MutableStateFlow("")
    val correo = _correo.asStateFlow()

    private val _clave = MutableStateFlow("")
    val clave = _clave.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    // Actualización de campos
    fun onNombreChanged(value: String) {
        _nombre.value = value
    }

    fun onCorreoChanged(value: String) {
        _correo.value = value
    }

    fun onClaveChanged(value: String) {
        _clave.value = value
    }

    // Validación de formulario
    fun validarFormulario(): Boolean {
        return when {
            _nombre.value.isEmpty() || _correo.value.isEmpty() || _clave.value.isEmpty() -> {
                _errorMessage.value = "Completa todos los campos"
                false
            }
            !_correo.value.contains("@") -> {
                _errorMessage.value = "Correo electrónico inválido"
                false
            }
            _clave.value.length < 4 -> {
                _errorMessage.value = "La contraseña debe tener al menos 4 caracteres"
                false
            }
            else -> {
                _errorMessage.value = ""
                true
            }
        }
    }
}
