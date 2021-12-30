package com.materoy.edvora.home.data.dto

import android.icu.text.SimpleDateFormat
import com.materoy.edvora.home.domain.model.Product
import java.time.LocalDate

data class ProductDto(
    val address: Address,
    val brand_name: String,
    val date: String,
    val discription: String,
    val image: String,
    val price: Int,
    val product_name: String,
    val time: String
) {
    fun toProduct(): Product {
        return Product(
            address = address,
            brandName = brand_name,
            date = SimpleDateFormat(date),
            description = discription,
            imageUrl = image,
            price = price,
            productName = product_name,
            time = SimpleDateFormat(time)
        );
    }
}