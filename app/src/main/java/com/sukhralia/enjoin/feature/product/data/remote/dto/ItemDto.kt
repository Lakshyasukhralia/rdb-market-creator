package com.sukhralia.enjoin.feature.product.data.remote.dto

import com.sukhralia.enjoin.feature.product.domain.models.Item

data class ItemDto(
    val category_id: String,
    val collectionId: String,
    val collectionName: String,
    val created: String,
    val id: String,
    val name: String,
    val updated: String
)

fun ItemDto.toItem() = Item(category_id, collectionId, collectionName, id, name)