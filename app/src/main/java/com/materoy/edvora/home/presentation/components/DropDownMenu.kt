package com.materoy.edvora.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.materoy.edvora.ui.theme.EdvoraTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.CheckCircleOutline
import com.materoy.edvora.home.presentation.Categories
import com.materoy.edvora.home.presentation.Filters

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FiltersDropDownButton(
    filters: Filters,
    onSelectItem: (Categories, String) -> Unit,
    onUpdateItems: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val icon = if (expanded)
        Icons.Filled.ArrowDropUp
    else
        Icons.Filled.ArrowDropDown

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { didExpand ->
            expanded = didExpand
            if (!didExpand) onUpdateItems()
        },
        modifier = Modifier
            .padding(0.dp)
            .clip(
                if (expanded) RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp
                ) else RoundedCornerShape(10.dp)
            ),
    ) {
        TextField(
            readOnly = true,
            value = "",
            placeholder = { Text("Filters") },
            onValueChange = { },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.primary,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = if (expanded) Color.Gray else Color.Transparent
            ),
            modifier = Modifier
                .width(200.dp)
                .padding(0.dp)
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                onUpdateItems()
            },
            modifier = Modifier
                .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp))
                .padding(vertical = 10.dp)
        ) {
            Categories.values().forEach { item ->
                val items: List<String> = when (item) {
                    Categories.Products -> filters.productNames
                    Categories.City -> filters.cities
                    Categories.State -> filters.states
                    else -> {
                        emptyList()
                    }
                }

                val selectedItems: List<String> = when (item) {
                    Categories.Products -> filters.productFilterList
                    Categories.City -> filters.citiesFilterList
                    Categories.State -> filters.stateFilterList
                    else -> {
                        emptyList()
                    }
                }

                DropdownMenuItem(
                    onClick = { }
                ) {
                    NestedDropDown(
                        title = item.name,
                        items = items,
                        selectedItems = selectedItems,
                        onSelectItem = { onSelectItem(item, it) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun NestedDropDown(
    title: String,
    items: List<String>,
    selectedItems: List<String>,
    onSelectItem: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(Modifier.padding(vertical = 5.dp)) {
        Row(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .clickable { expanded = !expanded }
                .background(color = MaterialTheme.colors.background)
                .padding(horizontal = 8.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = title)
            ExposedDropdownMenuDefaults.TrailingIcon(
                expanded = expanded, onIconClick = { expanded = !expanded }
            )
        }
        DropdownMenu(
            expanded = expanded, onDismissRequest = { expanded = false }, modifier = Modifier.clip(
                RoundedCornerShape(15.dp)
            )
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = { onSelectItem(item) },
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp))
                        .background(MaterialTheme.colors.surface)
                ) {
                    Row(
                        Modifier
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.CheckCircleOutline,
                            contentDescription = null,
                            tint = if (selectedItems.contains(item)) Color.Blue else Color.Transparent
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(item)
                    }
                }
            }
        }
    }
}
