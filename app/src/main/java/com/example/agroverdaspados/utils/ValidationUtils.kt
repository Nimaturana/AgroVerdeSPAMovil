package com.example.agroverdaspados.utils
import androidx.core.util.PatternsCompat
// validaciones de email, contraseña y si esta vacio
// se usa en formularios como el login y registro
object ValidationUtils {

    // se soluciona bypass erroneo por culpa de codigo
    // se mejoran validaciones
    fun isValidEmail(email: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches();
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= 4
    }

    fun isNotEmpty(vararg fields: String): Boolean {
        return fields.all { it.isNotBlank() }
    }
}
