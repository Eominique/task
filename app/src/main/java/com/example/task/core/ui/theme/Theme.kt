package com.example.task.core.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable


@Composable
fun TaskTheme( content: @Composable () -> Unit
) {
    val colors =  lightColorPalette

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = Shapes,
        content = content
    )

}