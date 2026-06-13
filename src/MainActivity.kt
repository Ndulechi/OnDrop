package com.example.ondrop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.ondrop.ui.theme.OnDropTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnDropTheme {
                var currentScreen by remember { mutableStateOf("login") }
                var selectedChatId by remember { mutableStateOf("") }
                
                when (currentScreen) {
                    "login" -> LoginScreen(
                        onNavigateToSignUp = { currentScreen = "signup" },
                        onLoginSuccess = { currentScreen = "home" }
                    )
                    "signup" -> SignUpScreen(
                        onNavigateBack = { currentScreen = "login" },
                        onSignUpSuccess = { currentScreen = "home" }
                    )
                    "home" -> HomeScreen(
                        onNavigateToChats = { currentScreen = "chatlist" },
                        onLogout = { currentScreen = "login" }
                    )
                    "chatlist" -> ChatListScreen(
                        onChatClick = { id -> 
                            selectedChatId = id
                            currentScreen = "chat"
                        },
                        onBack = { currentScreen = "home" }
                    )
                    "chat" -> ChatScreen(
                        chatId = selectedChatId,
                        onBack = { currentScreen = "chatlist" }
                    )
                }
            }
        }
    }
}
