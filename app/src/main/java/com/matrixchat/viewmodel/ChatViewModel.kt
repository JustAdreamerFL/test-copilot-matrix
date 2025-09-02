package com.matrixchat.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matrixchat.data.MatrixClient
import com.matrixchat.ui.screens.Message
import kotlinx.coroutines.launch

data class ChatUiState(
    val messages: List<Message> = emptyList(),
    val currentMessage: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val isConnected: Boolean = false
)

class ChatViewModel(
    private val matrixClient: MatrixClient?
) : ViewModel() {
    var uiState by mutableStateOf(
        ChatUiState(
            messages = listOf(
                Message(
                    id = "welcome",
                    content = "Welcome to Matrix Chat! This is a demo implementation.",
                    sender = "System",
                    timestamp = System.currentTimeMillis() - 60000,
                    isFromCurrentUser = false
                ),
                Message(
                    id = "demo",
                    content = "You can send messages using the input field below. In a real implementation, these would be sent via the Matrix protocol.",
                    sender = "System",
                    timestamp = System.currentTimeMillis() - 30000,
                    isFromCurrentUser = false
                )
            ),
            isConnected = matrixClient?.isLoggedIn() == true
        )
    )
        private set
    
    init {
        // Initialize with demo data and check connection
        if (matrixClient?.isLoggedIn() == true) {
            syncRooms()
        }
    }
    
    fun updateCurrentMessage(message: String) {
        uiState = uiState.copy(currentMessage = message)
    }
    
    fun sendMessage() {
        val messageText = uiState.currentMessage.trim()
        if (messageText.isEmpty()) return
        
        val newMessage = Message(
            id = System.currentTimeMillis().toString(),
            content = messageText,
            sender = matrixClient?.getCurrentUserId() ?: "You",
            timestamp = System.currentTimeMillis(),
            isFromCurrentUser = true
        )
        
        // Add message to UI immediately
        uiState = uiState.copy(
            messages = uiState.messages + newMessage,
            currentMessage = "",
            isLoading = true
        )
        
        // Simulate sending via Matrix API
        viewModelScope.launch {
            try {
                val result = matrixClient?.sendMessage("!demo:matrix.org", messageText)
                if (result?.isSuccess == true) {
                    // Message sent successfully
                    uiState = uiState.copy(isLoading = false)
                    
                    // Simulate receiving a response after a delay
                    kotlinx.coroutines.delay(2000)
                    val responseMessage = Message(
                        id = "response_${System.currentTimeMillis()}",
                        content = "Message received! (This is a demo response)",
                        sender = "Matrix Bot",
                        timestamp = System.currentTimeMillis(),
                        isFromCurrentUser = false
                    )
                    uiState = uiState.copy(messages = uiState.messages + responseMessage)
                } else {
                    uiState = uiState.copy(
                        isLoading = false,
                        errorMessage = "Failed to send message"
                    )
                }
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Failed to send message"
                )
            }
        }
    }
    
    private fun syncRooms() {
        viewModelScope.launch {
            try {
                val result = matrixClient?.syncRooms()
                if (result?.isSuccess == true) {
                    // Rooms synced successfully
                    uiState = uiState.copy(isConnected = true)
                }
            } catch (e: Exception) {
                uiState = uiState.copy(
                    isConnected = false,
                    errorMessage = "Failed to sync with Matrix server"
                )
            }
        }
    }
    
    fun clearError() {
        uiState = uiState.copy(errorMessage = "")
    }
}