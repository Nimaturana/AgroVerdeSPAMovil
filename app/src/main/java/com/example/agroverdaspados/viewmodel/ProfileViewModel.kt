package com.example.agroverdaspados.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.agroverdaspados.data.local.UserSessionManager
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
    private val sessionManager = UserSessionManager(application.applicationContext)

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        observeSavedAvatar()
        observeUserEmail()
    }

    //  Escucha el correo guardado en DataStore
    private fun observeUserEmail() {
        viewModelScope.launch {
            sessionManager.getUserEmail().collectLatest { email ->
                _uiState.update {
                    it.copy(
                        userEmail = email ?: "Sin correo guardado",
                        userName = email?.substringBefore("@")?.replaceFirstChar { c -> c.uppercase() } ?: "Usuario"
                    )
                }
            }
        }
    }

    // Escucha cambios en el avatar
    private fun observeSavedAvatar() {
        viewModelScope.launch {
            avatarRepository.getAvatarUri().collectLatest { savedUri ->
                _uiState.update { it.copy(avatarUri = savedUri, isLoading = false) }
            }
        }
    }

    fun updateAvatar(uri: Uri?) {
        viewModelScope.launch {
            avatarRepository.saveAvatarUri(uri)
        }
    }
}