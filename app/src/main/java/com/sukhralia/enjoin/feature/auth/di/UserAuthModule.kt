package com.sukhralia.enjoin.feature.auth.di

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.sukhralia.enjoin.feature.auth.data.remote.api.UserAuthApi
import com.sukhralia.enjoin.feature.auth.data.repository.UserAuthRepositoryImpl
import com.sukhralia.enjoin.feature.auth.domain.repository.UserAuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UserAuthModule {

    @Provides
    @Singleton
    fun provideUserAuthApi(httpClient: OkHttpClient): UserAuthApi {
        return Retrofit.Builder().baseUrl(UserAuthApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
            .create(UserAuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserAuthRepository(api: UserAuthApi): UserAuthRepository {
        return UserAuthRepositoryImpl(userAuthApi = api)
    }
}