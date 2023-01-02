package com.sukhralia.enjoin.feature.auth.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sukhralia.enjoin.feature.auth.enum.AuthType
import com.sukhralia.enjoin.feature.destinations.LoginScreenDestination
import com.sukhralia.enjoin.feature.destinations.ProductFeedScreenDestination
import com.sukhralia.enjoin.feature.destinations.SignUpScreenDestination

@Composable
@Destination(start = true)
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: UserAuthViewModel = hiltViewModel()
) {
    if (viewModel.getAuthToken().isNullOrEmpty().not())
        navigator.navigate(ProductFeedScreenDestination)
    else HomeView(navigator)
}

@Composable
fun HomeView(navigator: DestinationsNavigator) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { navigator.navigate(LoginScreenDestination) }) {
            Text(text = AuthType.LOGIN.text)
        }
        Button(
            onClick = { navigator.navigate(SignUpScreenDestination) }) {
            Text(text = AuthType.SIGNUP.text)
        }
    }
}