package com.materoy.edvora.home.presentation

import com.materoy.edvora.home.domain.model.Product

data class HomeState(
    val uniqueNames: List<String>,
    val products: List<Product>,
    val isLoading: Boolean = false
)