package com.materoy.edvora.home.data.repository

import com.materoy.edvora.core.util.Resource
import com.materoy.edvora.home.data.EdvoraApi
import com.materoy.edvora.home.domain.model.Product
import com.materoy.edvora.home.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ProductRepositoryImpl(
    private val api: EdvoraApi
): ProductRepository{
    override fun getProducts(): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading<List<Product>>())

        try {
            val products = api.getProducts()
            emit(Resource.Success<List<Product>>(products.map { it.toProduct() }))

        } catch (e: HttpException){
            println(e)
            emit(Resource.Error<List<Product>>(
                "Ooops, something went wrong",
            ))
        } catch (e: IOException) {
            println(e)
            emit(Resource.Error<List<Product>>(
                "Couldn't reach server check your internet connection",
            ))
        }
    }
}