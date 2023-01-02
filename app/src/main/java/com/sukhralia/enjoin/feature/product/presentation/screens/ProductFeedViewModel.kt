package com.sukhralia.enjoin.feature.product.presentation.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukhralia.enjoin.core.util.Resource
import com.sukhralia.enjoin.feature.product.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductFeedViewModel @Inject constructor(private val repository: ProductRepository) :
    ViewModel() {

    var state by mutableStateOf(ItemState())

    init {
        getNearbyPlaces()
    }

    private fun getNearbyPlaces() {
        viewModelScope.launch {
            repository.getItems()
                .collect { result ->
                    state = when (result) {
                        is Resource.Success -> {
                            state.copy(
                                itemList = result.data,
                                error = ""
                            )
                        }
                        is Resource.Error -> {
                            state.copy(
                                error = result.message.toString(),
                            )
                        }
                        is Resource.Loading -> {
                            state.copy(
                                isLoading = result.isLoading,
                            )
                        }
                    }
                }
        }
    }

}