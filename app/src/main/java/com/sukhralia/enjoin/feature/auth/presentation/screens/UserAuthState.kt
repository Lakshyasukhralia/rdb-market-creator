package com.sukhralia.enjoin.feature.auth.presentation.screens

data class UserAuthState(
    val isLoading: Boolean = false,
    var error: String = "",
    val isSignedUp: Boolean = false,
    val isSignedIn: Boolean = false,
)