package com.sukhralia.enjoin.feature.product.data.remote.api

import com.sukhralia.enjoin.feature.auth.data.remote.api.UserAuthApi
import com.sukhralia.enjoin.feature.product.data.remote.dto.ItemResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {

    @GET("${UserAuthApi.BASE_URL}/api/collections/item/records")
    suspend fun getItems(): Response<ItemResponseDto>

    companion object {

        const val BASE_URL = "http://172.105.39.153"

    }
}