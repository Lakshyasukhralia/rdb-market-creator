package com.sukhralia.enjoin.core.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

abstract class ResponseHandler {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>) =
        flow {
            try {
                emit(Resource.Loading(true))
                val response = apiCall()
                if (response.isSuccessful) {
                    val body = response.body()
                    emit(Resource.Success(body))
                } else {
                    emit(Resource.Error(message = response.message(), statusCode = response.code()))
                }
            } catch (e: Exception) {
                emit(Resource.Error(message = e.message ?: e.toString()))
            }
            emit(Resource.Loading(false))
        }.flowOn(Dispatchers.IO)
}