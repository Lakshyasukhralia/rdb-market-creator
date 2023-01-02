package com.sukhralia.enjoin.feature.auth.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay

@Composable
fun ErrorView(error: String) {

    var show by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = Unit) {
        delay(1500)
        show = false
    }

    Column(modifier = Modifier.fillMaxSize()) {
        if (show) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.Red),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = error,
                    color = Color.White,
                )
            }
        }
    }

}