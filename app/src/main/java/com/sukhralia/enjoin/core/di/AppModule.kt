package com.sukhralia.enjoin.core.di

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.sukhralia.enjoin.core.persistence.datastore.preference.AppPreferenceKeys
import com.sukhralia.enjoin.core.persistence.datastore.preference.AppPreferenceRepository
import com.sukhralia.enjoin.core.persistence.datastore.preference.AppPreferenceRepositoryImpl
import com.sukhralia.enjoin.core.util.AppInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHttpClient(
        app: Application,
        appInterceptor: AppInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                ChuckerInterceptor.Builder(app)
                    .collector(ChuckerCollector(app))
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(false)
                    .build()
            )
//            .addInterceptor(appInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideDataStoreRepository(app: Application): AppPreferenceRepository =
        AppPreferenceRepositoryImpl(app)

    @Singleton
    @Provides
    fun provideAppInterceptor(preferenceRepository: AppPreferenceRepository): AppInterceptor =
        AppInterceptor(preferenceRepository)
}