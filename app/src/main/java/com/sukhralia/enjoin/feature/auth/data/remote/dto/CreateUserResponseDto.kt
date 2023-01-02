package com.sukhralia.enjoin.feature.auth.data.remote.dto

import com.sukhralia.enjoin.feature.auth.domain.models.User

data class CreateUserResponseDto(
    val avatar: String,
    val collectionId: String,
    val collectionName: String,
    val created: String,
    val emailVisibility: Boolean,
    val id: String,
    val name: String,
    val updated: String,
    val username: String,
    val verified: Boolean
)

fun CreateUserResponseDto.toUser() = User(avatar, id, name, username, verified)