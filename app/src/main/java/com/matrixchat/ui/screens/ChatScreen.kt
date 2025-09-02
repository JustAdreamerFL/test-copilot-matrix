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

data class Message(
    val id: String,
    val content: String,
    val sender: String,
    val timestamp: Long,
    val isFromCurrentUser: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen() {
    var messageText by remember { mutableStateOf("") }
    var messages by remember { 
        mutableStateOf(
            listOf(
                Message(
                    id = "1",
                    content = "Welcome to Matrix Chat! This is a demo message.",
                    sender = "System",
                    timestamp = System.currentTimeMillis() - 60000,
                    isFromCurrentUser = false
                ),
                Message(
                    id = "2",
                    content = "Hello! Nice to meet you.",
                    sender = "You",
                    timestamp = System.currentTimeMillis() - 30000,
                    isFromCurrentUser = true
                )
            )
        )
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Header
        TopAppBar(
            title = {
                Text(
                    text = "Matrix Chat",
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )

        // Messages list
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            reverseLayout = true
        ) {
            items(messages.reversed()) { message ->
                MessageItem(
                    message = message,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }

        // Message input
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = messageText,
                onValueChange = { messageText = it },
                placeholder = { Text(stringResource(R.string.type_message)) },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(
                    onSend = {
                        if (messageText.isNotEmpty()) {
                            val newMessage = Message(
                                id = System.currentTimeMillis().toString(),
                                content = messageText,
                                sender = "You",
                                timestamp = System.currentTimeMillis(),
                                isFromCurrentUser = true
                            )
                            messages = messages + newMessage
                            messageText = ""
                        }
                    }
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            FloatingActionButton(
                onClick = {
                    if (messageText.isNotEmpty()) {
                        val newMessage = Message(
                            id = System.currentTimeMillis().toString(),
                            content = messageText,
                            sender = "You",
                            timestamp = System.currentTimeMillis(),
                            isFromCurrentUser = true
                        )
                        messages = messages + newMessage
                        messageText = ""
                    }
                },
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