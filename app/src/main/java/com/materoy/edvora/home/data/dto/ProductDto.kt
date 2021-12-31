package com.materoy.edvora.home.data.dto

import android.icu.text.SimpleDateFormat
import com.google.gson.annotations.SerializedName
import com.materoy.edvora.home.domain.model.Product
import java.util.*

data class ProductDto(
    val address: Address,
    val brand_name: String,
    val date: String,
    @SerializedName("discription")
    val description: String,
    val image: String,
    val price: Int,
    val product_name: String,
    val time: String
) {
    fun toProduct(): Product {
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val dateObject: Date = dateFormatter.parse(date)
        val formattedDate = SimpleDateFormat("dd:MM:yyyy").format(dateObject)

        return Product(
            address = address,
            brandName = brand_name,
            date = formattedDate,
            description = description,
            imageUrl = image,
            price = price,
            productName = product_name,
            time = time
        );
    }
}