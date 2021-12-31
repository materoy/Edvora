package com.materoy.edvora.home.presentation.components

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.materoy.edvora.home.data.dto.Address
import com.materoy.edvora.home.domain.model.Product
import com.materoy.edvora.ui.theme.EdvoraTheme

@Composable
fun ProductCategoryView(title: String, products: List<Product>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .padding(start = 20.dp)
                .padding(vertical = 5.dp)
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp)
                .padding(vertical = 8.dp)
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            contentPadding = PaddingValues(start = 20.dp)
        ) {
            items(products) { product ->
                ProductCard(product = product)
            }
        }
    }
}


@Preview
@Composable
fun ProductCategoryViewPreview() {
    EdvoraTheme {
        ProductCategoryView(
            "Shoes",
            listOf<Product>(
                Product(
                    productName = "Tencent Limited",
                    brandName = "Tencent",
                    price = 1300,
                    address = Address(state = "Uttarakhand", city = "Pithoragarh"),
                    description = "Its a good product",
                    date = "",
                    time = "",
                    imageUrl = "https://toppng.com/uploads/preview/sitemap-infos-transparent-i-phone-x-phone-in-hand-11563198189tafc9ocrkg.png"
                )
            )
        )
    }
}
