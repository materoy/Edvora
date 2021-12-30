package com.materoy.edvora.home.presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.materoy.edvora.ui.theme.EdvoraTheme

@Composable
fun FiltersDropDownButton() {
    Box(
        modifier = Modifier
            .width(100.dp)
            .wrapContentSize(Alignment.TopStart)
            .border(0.5.dp, MaterialTheme.colors.onSurface.copy(0.5f))
    ) {
        Button(onClick = { /*TODO*/ }){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Text(
                    "Filters",
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Icon(
                    imageVector = Icons.Default.ArrowDropDown, contentDescription = null,
                    modifier = Modifier
                        .size(20.dp, 20.dp),
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}

@Preview
@Composable
fun FiltersDropDownButtonPreview() {
    EdvoraTheme {
        FiltersDropDownButton()
    }
}
