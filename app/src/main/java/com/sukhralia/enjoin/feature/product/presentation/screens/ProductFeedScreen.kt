package com.sukhralia.enjoin.feature.product.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.sukhralia.enjoin.feature.auth.presentation.components.ErrorView
import com.sukhralia.enjoin.feature.product.domain.models.Item
import com.sukhralia.enjoin.feature.product.presentation.components.ProductCard

@Destination
@Composable
fun ProductFeedScreen(
    viewModel: ProductFeedViewModel = hiltViewModel()
) {
    val state = viewModel.state

    state.itemList?.let { ProductFeedView(it) }

    if (state.error.isNotEmpty()) {
        ErrorView(error = state.error)
    }
}

@Composable
fun ProductFeedView(itemList: List<Item>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray)
    ) {
        items(itemList) {
            ProductCard(it)
        }
    }
}