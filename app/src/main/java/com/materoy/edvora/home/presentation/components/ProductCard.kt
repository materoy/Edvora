package com.materoy.edvora.home.presentation.components

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.materoy.edvora.home.data.dto.Address
import com.materoy.edvora.home.domain.model.Product
import com.materoy.edvora.ui.theme.EdvoraTheme
import com.skydoves.landscapist.glide.GlideImage
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.primarySurface
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ProductCard(product: Product) {
    Box(Modifier.padding(horizontal = 5.dp)) {
        Card(
            Modifier
                .clip(RoundedCornerShape(10))
                .width(250.dp)
                .height(170.dp),
            backgroundColor = MaterialTheme.colors.primaryVariant
        ) {
            Column(Modifier.padding(15.dp), verticalArrangement = Arrangement.SpaceBetween) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(Modifier.height(120.dp).width(120.dp), Arrangement.SpaceBetween) {
                        // Product image
                        GlideImage(
                            imageModel = product.imageUrl, modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(12)),
                            contentScale = ContentScale.Fit
                        )

                        // Location
                        Text(
                            text = product.address.state,
                            style = MaterialTheme.typography.caption.copy(
                                fontSize = 14.sp,
                                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                            ),
                            modifier = Modifier
                        )
                        Text(
                            text = product.address.city,
                            style = MaterialTheme.typography.caption.copy(
                                fontSize = 12.sp,
                                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                            ),
                            modifier = Modifier
                        )
                    }
                    Spacer(modifier = Modifier.width(15.dp))

                    Column(
                        Modifier.height(115.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Product name
                        Text(
                            text = product.productName,
                            style = MaterialTheme.typography.h6.copy(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                        )

                        // Brand name
                        Text(
                            text = product.brandName,
                            style = MaterialTheme.typography.h6.copy(
                                fontSize = 16.sp,
                                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                            ),
                            modifier = Modifier
                        )

                        // Price
                        Text(
                            text = "\$ ${product.price.toDouble()}",
                            style = MaterialTheme.typography.body2.copy(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                        )

                        // Date
                        Text(
                            text = "Date: ${product.date}",
                            style = MaterialTheme.typography.caption.copy(
                                fontSize = 12.sp,
                                color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
                            ),
                            modifier = Modifier
                        )
                    }
                }

                // Product description
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
                    ),
                    modifier = Modifier
                )
            }
        }
    }
}


@Preview
@Composable
fun ProductCardPreview() {
    EdvoraTheme {
        ProductCard(
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
    }
}