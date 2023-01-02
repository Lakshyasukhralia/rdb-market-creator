package com.sukhralia.enjoin.feature.auth.data.remote.dto

data class AuthenticateRequestDto(
    val identity: String,
    val password: String
)