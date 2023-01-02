package com.sukhralia.enjoin.feature.auth.domain.usecase

data class UserAuthUseCases(
    val createUserUseCase: CreateUserUseCase,
    val authenticateUserUseCase: AuthenticateUserUseCase
)