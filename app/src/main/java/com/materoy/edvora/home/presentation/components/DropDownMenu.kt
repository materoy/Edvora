package com.materoy.edvora.home.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.materoy.edvora.ui.theme.EdvoraTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FiltersDropDownButton() {
    var filterDropDownEnabled by remember { mutableStateOf(false) }
    val dropDownItems = listOf<String>("Products", "State", "City")
    val icon = if (filterDropDownEnabled)
        Icons.Filled.ArrowDropUp
    else
        Icons.Filled.ArrowDropDown

    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    ExposedDropdownMenuBox(
        expanded = filterDropDownEnabled,
        onExpandedChange = { didExpand -> filterDropDownEnabled = didExpand }) {
        TextField(
            readOnly = true,
            value = "Filters",
            onValueChange = { },
            label = { Text("Label") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = filterDropDownEnabled
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = filterDropDownEnabled,
            onDismissRequest = {
                filterDropDownEnabled = false
            }
        ) {
            dropDownItems.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        filterDropDownEnabled = false
                    }
                ) {
                    Text(text = selectionOption)
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .width(150.dp)
            .wrapContentSize(Alignment.TopStart)
            .border(0.5.dp, MaterialTheme.colors.onSurface.copy(0.5f))
    ) {
        OutlinedTextField(
            value = "Filters",
            onValueChange = { },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textFieldSize = coordinates.size.toSize()
                },
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { filterDropDownEnabled = !filterDropDownEnabled })
            }
        )
        DropdownMenu(
            expanded = filterDropDownEnabled,
            onDismissRequest = { filterDropDownEnabled = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            dropDownItems.forEach { label: String ->
                DropdownMenuItem(onClick = { }) {
                    Text(text = label)
                }
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
