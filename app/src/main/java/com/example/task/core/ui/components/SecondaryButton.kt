package com.example.task.core.ui.components

import android.content.res.Configuration
import android.text.BoringLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import com.example.task.R
import com.example.task.core.ui.theme.ButtonShape
import com.example.task.core.ui.theme.TaskTheme

/**
 * This is a custom [TextButton] that provides the shape and styling expected
 * in the TOA application.
 *
 * @param[text] The text inside the button.
 * @param[onClick] A callback invoked when the user clicks the button.
 * @param[modifier] An optional [Modifier] to configure this component.
 */

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colors.primary,
    enabled: Boolean = true
) {

    val buttonColors = ButtonDefaults.textButtonColors(
        contentColor = contentColor,
    )

    TextButton(
        onClick = onClick,
        shape = ButtonShape,
        modifier = modifier
            .height(dimensionResource(id = R.dimen.button_height))
            .fillMaxWidth(),
        colors = contentColor as ButtonColors,
        enabled = enabled
    ) {
        Text(
            text = text.toUpperCase(Locale.current),
        )
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    group = "enabled"
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    group = "enabled"
)
@Composable
@Suppress("UnusedPrivateMember")
private fun SecondaryButtonPreview() {
    TaskTheme {
        Surface {
            SecondaryButton(
                text = "Primary button",
                onClick = {},
            )
        }
    }
}

@Preview(
    name = "Night Mode - Disabled",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    group = "disabled",
)
@Preview(
    name = "Day Mode - Disabled",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    group = "disabled",
)
@Composable
@Suppress("UnusedPrivateMember")
private fun DisabledSecondaryButtonPreview() {
    TaskTheme {
        Surface {
            SecondaryButton(
                text = "Primary button",
                onClick = {},
                enabled = false,
            )
        }
    }
}

