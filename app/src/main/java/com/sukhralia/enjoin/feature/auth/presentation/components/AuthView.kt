package com.sukhralia.enjoin.feature.auth.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AuthView(
    buttonLabel: String,
    onButtonClick: ((String, String) -> Unit)
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.padding(vertical = 10.dp)) {
            TextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = { Text("Email") }
            )
        }

        Box(modifier = Modifier.padding(vertical = 10.dp)) {
            TextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = { Text("Password") }
            )
        }

        Button(
            onClick = { onButtonClick.invoke(email, password) }) {
            Text(text = buttonLabel)
        }
    }
}