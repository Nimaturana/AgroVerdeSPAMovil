package com.example.agroverdaspados.viewmodel

import android.app.Application
import com.example.agroverdaspados.data.local.UserDataStore
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel
    private val userDataStore = mockk<UserDataStore>()
    private val app = mockk<Application>(relaxed = true)

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)

        // mock datos existentes

        coEvery { userDataStore.getUserEmail() } returns flowOf("test@gmail.com")
        coEvery { userDataStore.getUserPassword() } returns flowOf("1234")

        viewModel = LoginViewModel(userDataStore, app)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `login falla si email esta vacio`() = runTest {
        viewModel.onEmailChanged("")
        viewModel.onPasswordChanged("1234")

        viewModel.login {}

        dispatcher.scheduler.advanceUntilIdle()

        assertEquals("Completa todos los campos", viewModel.errorMessage.value)
    }

    @Test
    fun `login falla por email sin arroba`() = runTest {
        viewModel.onEmailChanged("testgmail.com")
        viewModel.onPasswordChanged("1234")

        viewModel.login {}

        dispatcher.scheduler.advanceUntilIdle()

        assertEquals("El correo electrónico debe contener '@'", viewModel.errorMessage.value)
    }

    @Test
    fun `login falla si email no coincide con DataStore`() = runTest {
        viewModel.onEmailChanged("otro@gmail.com")
        viewModel.onPasswordChanged("1234")

        viewModel.login {}

        dispatcher.scheduler.advanceUntilIdle()

        assertEquals("Email y/o Contraseña incorrecta, intentar nuevamente", viewModel.errorMessage.value)
    }

    @Test
    fun `login exitoso cuando coincide email y password`() = runTest {
        viewModel.onEmailChanged("test@gmail.com")
        viewModel.onPasswordChanged("1234")

        var successCalled = false

        viewModel.login {
            successCalled = true
        }

        dispatcher.scheduler.advanceUntilIdle()

        assertEquals("", viewModel.errorMessage.value)
        assertEquals(true, successCalled)
    }
}
