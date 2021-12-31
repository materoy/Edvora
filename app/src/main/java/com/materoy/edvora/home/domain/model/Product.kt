package com.materoy.edvora.home.domain.model

import com.materoy.edvora.home.data.dto.Address

data class Product(
    val address: Address,
    val brandName: String,
    val date: String,
    val description: String,
    val imageUrl: String,
    val price: Int,
    val productName: String,
    val time: String
)
