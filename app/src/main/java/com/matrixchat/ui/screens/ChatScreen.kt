package com.matrixchat.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.matrixchat.R
import com.matrixchat.ui.components.MessageItem
import com.matrixchat.viewmodel.ChatViewModel

data class Message(
    val id: String,
    val content: String,
    val sender: String,
    val timestamp: Long,
    val isFromCurrentUser: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    chatViewModel: ChatViewModel
) {
    val uiState = chatViewModel.uiState

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Header
        TopAppBar(
            title = {
                Column {
                    Text(
                        text = "Matrix Chat",
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = if (uiState.isConnected) "Connected" else "Disconnected",
                        style = MaterialTheme.typography.bodySmall,
                        color = if (uiState.isConnected) 
                            MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                        else 
                            MaterialTheme.colorScheme.error
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )

        // Error message
        if (uiState.errorMessage.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Text(
                    text = uiState.errorMessage,
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onErrorContainer
                )
            }
        }

        // Messages list
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            reverseLayout = true
        ) {
            items(uiState.messages.reversed()) { message ->
                MessageItem(
                    message = message,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }

        // Loading indicator
        if (uiState.isLoading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Message input
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = uiState.currentMessage,
                onValueChange = chatViewModel::updateCurrentMessage,
                placeholder = { Text(stringResource(R.string.type_message)) },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(
                    onSend = { chatViewModel.sendMessage() }
                ),
                enabled = !uiState.isLoading
            )

            Spacer(modifier = Modifier.width(8.dp))

            FloatingActionButton(
                onClick = chatViewModel::sendMessage,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = stringResource(R.string.send)
                )
            }
        }
    }
}