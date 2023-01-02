package com.sukhralia.enjoin.feature.auth.domain.repository

import com.sukhralia.enjoin.core.util.Resource
import com.sukhralia.enjoin.feature.auth.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserAuthRepository {
    suspend fun createUser(
        email: String,
        name: String,
        password: String,
        passwordConfirm: String
    ): Flow<Resource<User>>

    suspend fun authenticateUser(identity: String, password: String): Flow<Resource<User>>
}