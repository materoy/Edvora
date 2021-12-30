package com.materoy.edvora.home.domain.model

import android.icu.text.SimpleDateFormat
import com.materoy.edvora.home.data.dto.Address
import java.time.LocalDate

data class Product(
    val address: Address,
    val brandName: String,
    val date: SimpleDateFormat,
    val description: String,
    val imageUrl: String,
    val price: Int,
    val productName: String,
    val time: SimpleDateFormat
)
