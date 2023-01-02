package com.sukhralia.enjoin.feature.product.presentation.screens

import com.sukhralia.enjoin.feature.product.domain.models.Item

data class ItemState(
    val isLoading: Boolean = false,
    var error: String = "",
    val itemList: List<Item>? = emptyList()
)