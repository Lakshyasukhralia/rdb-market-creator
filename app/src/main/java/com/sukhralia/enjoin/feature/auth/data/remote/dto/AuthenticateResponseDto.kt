package com.sukhralia.enjoin.feature.auth.data.remote.dto

import com.sukhralia.enjoin.feature.auth.domain.models.User

data class AuthenticateResponseDto(
    val record: UserRecordDto,
    val token: String
)

fun AuthenticateResponseDto.toUser() =
    User(record.avatar, record.id, record.name, record.username, record.verified, token)