package com.sukhralia.enjoin.feature.auth.domain.models

data class User(
    val avatar: String,
    val id: String,
    val name: String,
    val username: String,
    val verified: Boolean,
    val token: String? = null
)

