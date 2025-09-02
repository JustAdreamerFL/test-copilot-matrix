package com.matrixchat.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrixchat.data.MatrixClient
import kotlinx.coroutines.launch

data class LoginUiState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val errorMessage: String = "",
    val username: String = "",
    val password: String = "",
    val homeserver: String = "https://matrix.org"
)

class LoginViewModel : ViewModel() {
    var uiState by mutableStateOf(LoginUiState())
        private set
    
    private var matrixClient: MatrixClient? = null
    
    fun updateUsername(username: String) {
        uiState = uiState.copy(username = username, errorMessage = "")
    }
    
    fun updatePassword(password: String) {
        uiState = uiState.copy(password = password, errorMessage = "")
    }
    
    fun updateHomeserver(homeserver: String) {
        uiState = uiState.copy(homeserver = homeserver, errorMessage = "")
    }
    
    fun login() {
        if (uiState.username.isEmpty() || uiState.password.isEmpty() || uiState.homeserver.isEmpty()) {
            uiState = uiState.copy(errorMessage = "Please fill in all fields")
            return
        }
        
        uiState = uiState.copy(isLoading = true, errorMessage = "")
        
        viewModelScope.launch {
            try {
                matrixClient = MatrixClient(uiState.homeserver)
                val result = matrixClient!!.login(uiState.username, uiState.password)
                
                if (result.isSuccess) {
                    uiState = uiState.copy(
                        isLoading = false,
                        isLoggedIn = true,
                        errorMessage = ""
                    )
                } else {
                    uiState = uiState.copy(
                        isLoading = false,
                        errorMessage = result.exceptionOrNull()?.message ?: "Login failed"
                    )
                }
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Unknown error occurred"
                )
            }
        }
    }
    
    fun getMatrixClient(): MatrixClient? = matrixClient
}