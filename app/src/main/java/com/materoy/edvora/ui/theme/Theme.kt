package com.materoy.edvora.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@SuppressLint("ConflictingOnColor")
private val colorPalette = lightColors(
    primary = Black87,
    primaryVariant = PureBlack,
    secondary = Gray,

    background = Black56,
    surface = Black87,
    onPrimary = PureWhite,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = PureWhite,
)

@Composable
fun EdvoraTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {


    MaterialTheme(
        colors = colorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}