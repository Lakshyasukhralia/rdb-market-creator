package com.sukhralia.enjoin.core.util

import com.sukhralia.enjoin.core.persistence.datastore.preference.AppPreferenceKeys
import com.sukhralia.enjoin.core.persistence.datastore.preference.AppPreferenceRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class AppInterceptor @Inject constructor(
    private val preferenceRepository: AppPreferenceRepository
) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { preferenceRepository.getString(AppPreferenceKeys.AUTHORIZATION_TOKEN) }
        val request: Request = chain.request()
        val requestBuilder: Request.Builder = request.newBuilder()
        token?.let { requestBuilder.addHeader("Authorization", "Bearer $token") }
        return chain.proceed(requestBuilder.build())
    }
}