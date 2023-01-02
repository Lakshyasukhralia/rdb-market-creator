package com.sukhralia.enjoin.feature.product.data.remote.dto

data class ItemResponseDto(
    val items: List<ItemDto>,
    val page: Int,
    val perPage: Int,
    val totalItems: Int,
    val totalPages: Int
)