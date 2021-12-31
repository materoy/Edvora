package com.materoy.edvora.home.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.materoy.edvora.home.data.dto.Address
import com.materoy.edvora.home.domain.model.Product
import com.materoy.edvora.ui.theme.EdvoraTheme
import kotlin.math.absoluteValue

@Composable
fun ProductCategoryView(title: String, products: List<Product>) {
    val listState by remember { mutableStateOf(LazyListState()) }

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
            contentPadding = PaddingValues(start = 20.dp),
            state = listState
        ) {
            items(products) { product ->
                ProductCard(product = product)
            }
        }

        if (products.size >= 3)
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val layoutInfo = listState.layoutInfo

                for (i in 0..2) {
                    val progress = ((products.size / 3) ).toInt()

                    Canvas(
                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp),
                        onDraw = {
                            drawCircle(
                                color = if (i == progress)
                                    Color.White else Color.Gray,
                                radius = 10.0f
                            )
                        })
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
