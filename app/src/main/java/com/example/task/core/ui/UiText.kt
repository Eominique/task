package com.example.task.core.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable

/**
 * This is a sealed class that contains all of the different ways text can be presented to the UI.
 */
sealed class UiText {
data class StringText(val value: String): UiText()
    data class ResourceText(@StringRes val value: Int): UiText()
}

/**
 * Evaluates the value of this [UIText] based on its type.
 *
 * @param[context] If necessary, use this to evaluate a string resource.
 */
fun UiText.getString(context: Context): String {
    return when (this) {
        is UiText.StringText -> this.value
        is UiText.ResourceText -> context.getString(this.value)
    }
}

/**
 * A helper function that allows to get strings from a [Composable] context.
 */
@Composable
fun UiText.getString(): String {
    return this.getString(androidx.compose.ui.platform.LocalContext.current)
}
