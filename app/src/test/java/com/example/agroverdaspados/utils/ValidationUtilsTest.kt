package com.example.agroverdaspados.utils

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidationUtilsTest {

    @Test
    fun `email valido debe retornar true`() {
        // Arrange
        val email = "test@gmail.com"

        // Act
        val result = ValidationUtils.isValidEmail(email)

        // Assert
        assertTrue(result)
    }

    @Test
    fun `email invalido sin arroba retorna false`() {
        val email = "testgmail.com"
        val result = ValidationUtils.isValidEmail(email)
        assertFalse(result)
    }

    @Test
    fun `email invalido sin com retorna false`() {
        val email = "test@gmail"
        val result = ValidationUtils.isValidEmail(email)
        assertFalse(result)
    }

    @Test
    fun `password corta debe retornar false`() {
        val result = ValidationUtils.isValidPassword("123")
        assertFalse(result)
    }

    @Test
    fun `campos vacios debe retornar false`() {
        val result = ValidationUtils.isNotEmpty("", "hola")
        assertFalse(result)
    }
}
