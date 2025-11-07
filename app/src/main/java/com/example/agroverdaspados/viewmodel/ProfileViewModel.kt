package com.example.agroverdaspados.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.agroverdaspados.data.local.UserDataStore
import com.example.agroverdaspados.model.ProfileUiState
import com.example.agroverdaspados.repository.AvatarRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val avatarRepository = AvatarRepository(application.applicationContext)
    private val userDataStore = UserDataStore(application.applicationContext)

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        observeSavedAvatar()
        observeUserData()
    }

    // carga nombre y correo reales desde DataStore
    private fun observeUserData() {
        viewModelScope.launch {
            userDataStore.getUserName().collectLatest { name ->
                userDataStore.getUserEmail().collectLatest { email ->
                    _uiState.update {
                        it.copy(
                            userName = name ?: "Usuario",
                            userEmail = email ?: "Sin correo guardado"
                        )
                    }
                }
            }
        }
    }

    // escucha cambios en el avatar guardado
    private fun observeSavedAvatar() {
        viewModelScope.launch {
            avatarRepository.getAvatarUri().collectLatest { savedUri ->
                _uiState.update { it.copy(avatarUri = savedUri, isLoading = false) }
            }
        }
    }

    // permite actualizar foto de perfil
    fun updateAvatar(uri: Uri?) {
        viewModelScope.launch {
            avatarRepository.saveAvatarUri(uri)
        }
    }
}
