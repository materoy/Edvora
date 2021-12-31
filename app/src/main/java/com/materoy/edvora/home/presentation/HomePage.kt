package com.materoy.edvora.home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.materoy.edvora.home.presentation.components.FiltersDropDownButton
import com.materoy.edvora.home.presentation.components.ProductCategoryView
import com.materoy.edvora.ui.theme.EdvoraTheme

@Composable
fun HomePage() {
    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.state

    Scaffold(Modifier, topBar = {
        Text(
            text = "Edvora",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
        )
    }, backgroundColor = MaterialTheme.colors.background) { innerPadding ->
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
                    FiltersDropDownButton(
                        state.filters,
                        onSelectItem = { category, selectedItem ->
                            viewModel.addFilter(
                                category,
                                selectedItem
                            )
                        },
                        onUpdateItems = { viewModel.onUpdateFilters() }
                    )

                    Button(
                        onClick = { viewModel.clearFilter() },
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .clip(RoundedCornerShape(5.dp))
                            .height(40.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary
                        )
                    ) {
                        Text(text = "Clear Filter")
                    }
                }


                // Products list
                LazyColumn(modifier = Modifier) {
                    // Only shows filtered product names if it exists
                    items(if (state.filters.productFilterList.isNotEmpty()) state.filters.productFilterList else state.filters.productNames) { productName: String ->
                        ProductCategoryView(
                            title = productName,
                            products = state.products.filter { product -> product.productName == productName })
                    }
                }
            }

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), Color.Green)
            }
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