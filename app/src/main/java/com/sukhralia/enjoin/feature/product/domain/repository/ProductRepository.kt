package com.sukhralia.enjoin.feature.product.domain.repository

import com.sukhralia.enjoin.core.util.Resource
import com.sukhralia.enjoin.feature.product.domain.models.Item
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getItems(): Flow<Resource<List<Item>>>
}