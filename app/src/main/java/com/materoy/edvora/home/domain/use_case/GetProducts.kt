package com.materoy.edvora.home.domain.use_case

import com.materoy.edvora.core.util.Resource
import com.materoy.edvora.home.domain.model.Product
import com.materoy.edvora.home.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProducts @Inject constructor(
    private val repository: ProductRepository
) {

    operator fun invoke(): Flow<Resource<List<Product>>> {
        return repository.getProducts()
    }
}