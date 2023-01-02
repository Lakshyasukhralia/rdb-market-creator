package com.sukhralia.enjoin.feature.auth.presentation.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukhralia.enjoin.core.persistence.datastore.preference.AppPreferenceKeys.AUTHORIZATION_TOKEN
import com.sukhralia.enjoin.core.persistence.datastore.preference.AppPreferenceRepository
import com.sukhralia.enjoin.core.util.Resource
import com.sukhralia.enjoin.feature.auth.domain.repository.UserAuthRepository
import com.sukhralia.enjoin.feature.auth.domain.usecase.CreateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class UserAuthViewModel @Inject constructor(
    private val repository: UserAuthRepository,
    private val prefRepository: AppPreferenceRepository,
    private val createUserUseCase: CreateUserUseCase,
) :
    ViewModel() {

    var state by mutableStateOf(UserAuthState())

    private fun validateUserNameAndPassword(email: String, password: String): Boolean {
        if (email.isEmpty() || password.isEmpty()) return false
        return true
    }

    fun createUser(email: String, name: String, password: String, passwordConfirm: String) {

        if (validateUserNameAndPassword(email, password).not()) {
            state = state.copy(error = "Invalid input")
            return
        }
        state = state.copy(error = "")

        viewModelScope.launch {
            createUserUseCase(email, name, password, passwordConfirm)
                .collect { result ->
                    state = when (result) {
                        is Resource.Success -> {
                            state.copy(
                                isSignedUp = true,
                                error = ""
                            )
                        }
                        is Resource.Error -> {
                            state.copy(
                                error = result.message.toString(),
                                isSignedUp = false
                            )
                        }
                        is Resource.Loading -> {
                            state.copy(
                                isLoading = result.isLoading,
                            )
                        }
                    }
                }
        }
    }

    fun authenticateUser(identity: String, password: String) {

        if (validateUserNameAndPassword(identity, password).not()) {
            state = state.copy(error = "Invalid input")
            return
        }
        state = state.copy(error = "")

        viewModelScope.launch {
            repository.authenticateUser(identity, password)
                .collect { result ->
                    state = when (result) {
                        is Resource.Success -> {
                            if (result.data?.token.isNullOrEmpty().not())
                                saveAuthToken(result.data?.token!!)
                            state.copy(
                                isSignedIn = true,
                                error = ""
                            )
                        }
                        is Resource.Error -> {
                            state.copy(
                                error = result.message.toString(),
                                isSignedIn = false
                            )
                        }
                        is Resource.Loading -> {
                            state.copy(
                                isLoading = result.isLoading,
                            )
                        }
                    }
                }
        }
    }

    private suspend fun saveAuthToken(token: String) {
        prefRepository.putString(AUTHORIZATION_TOKEN, token)
    }

    fun getAuthToken(): String? = runBlocking {
        prefRepository.getString(AUTHORIZATION_TOKEN)
    }
}