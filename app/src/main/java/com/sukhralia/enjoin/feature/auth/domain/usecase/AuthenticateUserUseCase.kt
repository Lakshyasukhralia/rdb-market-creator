package com.sukhralia.enjoin.feature.auth.domain.usecase

import com.sukhralia.enjoin.feature.auth.domain.repository.UserAuthRepository
import javax.inject.Inject

class AuthenticateUserUseCase @Inject constructor(private val userAuthRepository: UserAuthRepository) {

    suspend operator fun invoke(identity: String, password: String) =
        userAuthRepository.authenticateUser(identity, password)

}
