package com.example.agroverdaspados.viewmodel

import android.app.Application
import com.example.agroverdaspados.data.local.UserDataStore
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RegisterViewModelTest {

    private lateinit var viewModel: RegisterViewModel
    private val userDataStore = mockk<UserDataStore>(relaxed = true)
    private val app = mockk<Application>(relaxed = true)
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = RegisterViewModel(userDataStore, app)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `falla si campos estan vacios`() = runTest {
        viewModel.onNombreChanged("")
        viewModel.onCorreoChanged("")
        viewModel.onClaveChanged("")

        viewModel.registrarUsuario {}

        assertEquals("Completa todos los campos", viewModel.errorMessage.value)
    }

    @Test
    fun `correo invalido muestra error`() = runTest {
        viewModel.onNombreChanged("Juan")
        viewModel.onCorreoChanged("correo_invalido")
        viewModel.onClaveChanged("1234")

        viewModel.registrarUsuario {}

        assertEquals("Correo electrónico inválido", viewModel.errorMessage.value)
    }

    @Test
    fun `password muy corta muestra error`() = runTest {
        viewModel.onNombreChanged("Juan")
        viewModel.onCorreoChanged("test@gmail.com")
        viewModel.onClaveChanged("12")

        viewModel.registrarUsuario {}

        assertEquals("La contraseña debe tener al menos 4 caracteres", viewModel.errorMessage.value)
    }

    @Test
    fun `registro exitoso guarda usuario`() = runTest {
        viewModel.onNombreChanged("Juan")
        viewModel.onCorreoChanged("test@gmail.com")
        viewModel.onClaveChanged("1234")

        var success = false

        viewModel.registrarUsuario { success = true }

        dispatcher.scheduler.advanceUntilIdle()

        coVerify {
            userDataStore.saveUser("Juan", "test@gmail.com", "1234")
        }

        assertEquals(true, success)
    }
}
