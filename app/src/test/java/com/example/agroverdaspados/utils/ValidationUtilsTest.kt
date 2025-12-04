package com.example.agroverdaspados.utils

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidationUtilsTest {

// pruebas email
    @Test
    fun `email valido debe retornar true`() {
        val email = "test@gmail.cl"
        val result = ValidationUtils.isValidEmail(email)
        assertTrue(result)
    }

    @Test
    fun `email valido con puntos y guion debe retornar true`() {
        val email = "nicolas.maturana-perez@example.com"
        assertTrue(ValidationUtils.isValidEmail(email))
    }

    @Test
    fun `email invalido sin arroba retorna false`() {
        val email = "testgmail.com"
        val result = ValidationUtils.isValidEmail(email)
        assertFalse(result)
    }

    @Test
    fun `email invalido sin dominio retorna false`() {
        val email = "test@gmail"
        val result = ValidationUtils.isValidEmail(email)
        assertFalse(result)
    }

    @Test
    fun `email invalido con espacios retorna false`() {
        val email = "test @gmail.com"
        assertFalse(ValidationUtils.isValidEmail(email))
    }

    @Test
    fun `email invalido con doble arroba retorna false`() {
        val email = "test@@gmail.com"
        assertFalse(ValidationUtils.isValidEmail(email))
    }

    // pruebas contraseñas

    @Test
    fun `password corta debe retornar false`() {
        val result = ValidationUtils.isValidPassword("123")
        assertFalse(result)
    }

    @Test
    fun `password valida debe retornar true`() {
        val result = ValidationUtils.isValidPassword("1234")
        assertTrue(result)
    }

    @Test
    fun `password con espacios pero suficiente largo debe retornar true`() {
        val result = ValidationUtils.isValidPassword("   1234   ")
        assertTrue(result)
    }

    // pruebas si hay campos vacios

    @Test
    fun `campos vacios debe retornar false`() {
        val result = ValidationUtils.isNotEmpty("", "hola")
        assertFalse(result)
    }

    @Test
    fun `todos los campos llenos retorna true`() {
        val result = ValidationUtils.isNotEmpty("hola", "mundo")
        assertTrue(result)
    }

    @Test
    fun `campo con solo espacios retorna false`() {
        val result = ValidationUtils.isNotEmpty("   ")
        assertFalse(result)
    }

    @Test
    fun `ningun campo retorna false si uno es blank`() {
        val result = ValidationUtils.isNotEmpty("texto", "   ", "otro")
        assertFalse(result)
    }
}
