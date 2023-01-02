package com.sukhralia.enjoin.feature.auth.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CreateUserRequestDto(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("passwordConfirm")
    val passwordConfirm: String
)
