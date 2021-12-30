package com.materoy.edvora.home.domain.repository

import com.materoy.edvora.core.util.Resource
import com.materoy.edvora.home.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getProducts(): Flow<Resource<List<Product>>>
}