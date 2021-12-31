package com.materoy.edvora.home.presentation

import com.materoy.edvora.home.domain.model.Product

data class HomeState(
    val products: List<Product>,
    val filteredProducts: List<Product> = emptyList(),
    val isLoading: Boolean = false,

    val filters: Filters
)


enum class Categories { Products, State, City}

data class Filters(
    val productFilterList: List<String> = emptyList(),
    val stateFilterList: List<String> = emptyList(),
    val citiesFilterList: List<String> = emptyList(),


    val productNames: List<String>,
    val states: List<String>,
    val cities: List<String>,
)