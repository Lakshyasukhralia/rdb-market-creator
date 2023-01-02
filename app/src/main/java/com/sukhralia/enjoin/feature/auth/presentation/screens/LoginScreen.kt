package com.sukhralia.enjoin.feature.auth.presentation.screens

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.sukhralia.enjoin.feature.auth.enum.AuthType
import com.sukhralia.enjoin.feature.auth.presentation.components.AuthView
import com.sukhralia.enjoin.feature.auth.presentation.components.ErrorView
import com.sukhralia.enjoin.feature.destinations.ProductFeedScreenDestination

@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
    viewModel: UserAuthViewModel = hiltViewModel()
) {
    val state = viewModel.state

    AuthView(
        buttonLabel = AuthType.LOGIN.text,
        onButtonClick = { email, password -> viewModel.authenticateUser(email, password) })

    if (state.error.isNotEmpty()) {
        ErrorView(error = state.error)
    }

    if (state.isSignedIn) {
        Toast.makeText(
            LocalContext.current,
            "Success!",
            Toast.LENGTH_SHORT
        ).show()
        navigator.navigate(ProductFeedScreenDestination)
    }

}