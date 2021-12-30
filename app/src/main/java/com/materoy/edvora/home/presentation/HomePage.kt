package com.materoy.edvora.home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.materoy.edvora.home.domain.model.Product
import com.materoy.edvora.ui.theme.EdvoraTheme
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun HomePage() {
    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.state

    Scaffold(Modifier, topBar = {
        Text(text = "Edvora")
    }) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(Modifier.fillMaxSize()) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    FiltersDropDownButton()

                    OutlinedButton(onClick = { /*TODO*/ }) {
                        Text(text = "Clear Filter")
                    }
                }


                // Products list
                LazyColumn(modifier = Modifier) {
                    items(state.uniqueNames) { productName: String ->
                        ProductCategoryView(
                            title = productName,
                            products = state.products.filter { product -> product.productName == productName })
                    }
                }
            }

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }

}

@Composable
fun ProductCategoryView(title: String, products: List<Product>) {
    Column(modifier = Modifier) {
        Text(text = title)

        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(products) { product ->
                ProductCard(product = product)
            }
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(Modifier) {
        Column(Modifier) {
            Row {
                GlideImage(imageModel = product.imageUrl, modifier = Modifier.size(100.dp))

                Column(Modifier) {
                    Text(
                        text = product.productName,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                    )


                    Text(
                        text = product.brandName,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                    )

                    Text(
                        text = "\$ ${product.price}",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                    )
                }
            }

            Row(Modifier) {
                Text(
                    text = "${product.address.state}, ${product.address.city}",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                )

                Text(
                    text = product.brandName,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                )
            }

            Text(
                text = product.description,
                style = MaterialTheme.typography.h6,
                modifier = Modifier
            )
        }
    }
}

@Preview
@Composable
fun HomePagePreview() {
    EdvoraTheme {
        HomePage()
    }
}