package com.sukhralia.enjoin.feature.product.di

import com.sukhralia.enjoin.feature.product.data.remote.api.ProductApi
import com.sukhralia.enjoin.feature.product.data.repository.ProductRepositoryImpl
import com.sukhralia.enjoin.feature.product.domain.repository.ProductRepository
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
object ProductModule {

    @Provides
    @Singleton
    fun providePlaceApi(httpClient: OkHttpClient): ProductApi {
        return Retrofit.Builder().baseUrl(ProductApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
            .create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun providePlaceRepository(api: ProductApi): ProductRepository {
        return ProductRepositoryImpl(api)
    }
}