package com.materoy.edvora.home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.materoy.edvora.ui.theme.EdvoraTheme

@Composable
fun HomePage() {
//    val viewModel: HomeViewModel = hiltViewModel()

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

                }
            }
        }
    }
}

@Composable
fun ProductCategoryView() {
    Column(modifier = Modifier) {

    }
}

@Composable
fun ProductCard() {

}

@Preview
@Composable
fun HomePagePreview() {
    EdvoraTheme {
        HomePage()
    }
}