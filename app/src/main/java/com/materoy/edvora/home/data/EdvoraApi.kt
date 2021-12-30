package com.materoy.edvora.home.data

import com.materoy.edvora.home.data.dto.ProductDto
import retrofit2.http.GET

interface EdvoraApi {

    @GET()
    suspend fun getProducts(): List<ProductDto>

    companion object {
     val BASE_URL = "https://assessment-edvora.herokuapp.com/"
    }
}