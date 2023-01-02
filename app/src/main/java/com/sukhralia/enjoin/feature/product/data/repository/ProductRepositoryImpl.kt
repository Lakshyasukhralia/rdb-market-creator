package com.sukhralia.enjoin.feature.product.data.repository

import com.sukhralia.enjoin.core.util.Resource
import com.sukhralia.enjoin.core.util.ResponseHandler
import com.sukhralia.enjoin.feature.product.data.remote.api.ProductApi
import com.sukhralia.enjoin.feature.product.data.remote.dto.toItem
import com.sukhralia.enjoin.feature.product.domain.models.Item
import com.sukhralia.enjoin.feature.product.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productApi: ProductApi) : ProductRepository,
    ResponseHandler() {
    override suspend fun getItems(): Flow<Resource<List<Item>>> = flow {
        safeApiCall { productApi.getItems() }.collect { res ->
            when (res) {
                is Resource.Success -> emit(Resource.Success(res.data?.items?.map { it.toItem() }))
                is Resource.Error -> emit(
                    Resource.Error(
                        message = res.message ?: "Something went wrong!",
                        statusCode = res.statusCode
                    )
                )
                is Resource.Loading -> emit(Resource.Loading(res.isLoading))
            }
        }
    }
}