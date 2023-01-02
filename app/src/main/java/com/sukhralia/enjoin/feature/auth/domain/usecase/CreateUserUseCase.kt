package com.sukhralia.enjoin.feature.auth.domain.usecase

import com.sukhralia.enjoin.feature.auth.domain.repository.UserAuthRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(private val userAuthRepository: UserAuthRepository) {

    suspend operator fun invoke(
        email: String,
        name: String,
        password: String,
        passwordConfirm: String
    ) = userAuthRepository.createUser(email, name, password, passwordConfirm)

}