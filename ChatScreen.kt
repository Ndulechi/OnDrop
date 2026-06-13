package com.example.ondrop

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    chatId: String,
    onBack: () -> Unit
) {
    var messageText by remember { mutableStateOf("") }
    val fakeMessages = remember { mutableStateListOf("Hello!", "How are you?", "Testing OnDrop") }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(chatId) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Type a message") }
                )
                Spacer(Modifier.width(8.dp))
                IconButton(onClick = {
                    if (messageText.isNotBlank()) {
                        fakeMessages.add(messageText)
                        messageText = ""
                    }
                }) {
                    Icon(Icons.Default.Send, "Send")
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(8.dp),
            reverseLayout = true
        ) {
            items(fakeMessages.reversed()) { msg ->
                Card(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
                    Text(msg, modifier = Modifier.padding(12.dp))
                }
            }
        }
    }
}
