package com.matrixchat

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.matrixchat.ui.screens.ChatScreen
import com.matrixchat.ui.screens.LoginScreen
import com.matrixchat.viewmodel.ChatViewModel
import com.matrixchat.viewmodel.LoginViewModel

@Composable
fun MatrixChatApp() {
    val navController = rememberNavController()
    var loginViewModel: LoginViewModel? by remember { mutableStateOf(null) }
    
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            val currentLoginViewModel: LoginViewModel = viewModel()
            
            LoginScreen(
                onLoginSuccess = { viewModel ->
                    loginViewModel = viewModel
                    navController.navigate("chat") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                loginViewModel = currentLoginViewModel
            )
        }
        composable("chat") {
            val matrixClient = loginViewModel?.getMatrixClient()
            val chatViewModel = remember { ChatViewModel(matrixClient) }
            
            ChatScreen(chatViewModel = chatViewModel)
        }
    }
}