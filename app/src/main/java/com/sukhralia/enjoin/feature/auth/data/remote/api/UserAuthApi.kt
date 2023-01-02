package com.sukhralia.enjoin.feature.auth.data.remote.api

import com.sukhralia.enjoin.feature.auth.data.remote.dto.AuthenticateRequestDto
import com.sukhralia.enjoin.feature.auth.data.remote.dto.AuthenticateResponseDto
import com.sukhralia.enjoin.feature.auth.data.remote.dto.CreateUserRequestDto
import com.sukhralia.enjoin.feature.auth.data.remote.dto.CreateUserResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAuthApi {

    @POST("$BASE_URL/api/collections/users/records")
    suspend fun createUser(@Body createUserRequest: CreateUserRequestDto): Response<CreateUserResponseDto>

    @POST("$BASE_URL/api/collections/users/auth-with-password")
    suspend fun authenticate(@Body authenticateRequest: AuthenticateRequestDto): Response<AuthenticateResponseDto>

    companion object {

        const val BASE_URL = "http://172.105.39.153"

    }
}