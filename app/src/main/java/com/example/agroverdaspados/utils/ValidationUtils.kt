package com.example.agroverdaspados.utils

// validaciones de email, contraseña y si esta vacio
// se usa en formularios como el login y registro
object ValidationUtils {

    fun isValidEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".")
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= 4
    }

    fun isNotEmpty(vararg fields: String): Boolean {
        return fields.all { it.isNotBlank() }
    }
}
