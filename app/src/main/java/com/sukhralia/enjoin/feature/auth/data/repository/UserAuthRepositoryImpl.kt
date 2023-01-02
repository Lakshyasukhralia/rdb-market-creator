package com.sukhralia.enjoin.feature.auth.data.repository

import com.sukhralia.enjoin.core.util.Resource
import com.sukhralia.enjoin.core.util.ResponseHandler
import com.sukhralia.enjoin.feature.auth.data.remote.api.UserAuthApi
import com.sukhralia.enjoin.feature.auth.data.remote.dto.AuthenticateRequestDto
import com.sukhralia.enjoin.feature.auth.data.remote.dto.CreateUserRequestDto
import com.sukhralia.enjoin.feature.auth.data.remote.dto.toUser
import com.sukhralia.enjoin.feature.auth.domain.models.User
import com.sukhralia.enjoin.feature.auth.domain.repository.UserAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserAuthRepositoryImpl @Inject constructor(private val userAuthApi: UserAuthApi) :
    UserAuthRepository, ResponseHandler() {

    override suspend fun createUser(
        email: String,
        name: String,
        password: String,
        passwordConfirm: String
    ): Flow<Resource<User>> = flow {
        safeApiCall {
            userAuthApi.createUser(
                CreateUserRequestDto(
                    email,
                    name,
                    password,
                    passwordConfirm
                )
            )
        }.collect {
            when (it) {
                is Resource.Success -> emit(Resource.Success(it.data?.toUser()))
                is Resource.Error -> emit(
                    Resource.Error(
                        message = it.message ?: "Something went wrong!", statusCode = it.statusCode
                    )
                )
                is Resource.Loading -> emit(Resource.Loading(it.isLoading))
            }
        }
    }

    override suspend fun authenticateUser(
        identity: String,
        password: String
    ): Flow<Resource<User>> = flow {
        safeApiCall {
            userAuthApi.authenticate(
                AuthenticateRequestDto(
                    identity,
                    password
                )
            )
        }.collect {
            when (it) {
                is Resource.Success -> emit(Resource.Success(it.data?.toUser()))
                is Resource.Error -> emit(
                    Resource.Error(
                        message = it.message ?: "Something went wrong!",
                        statusCode = it.statusCode
                    )
                )
                is Resource.Loading -> emit(Resource.Loading(it.isLoading))
            }
        }
    }

}