package com.example.task.ui.login

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.task.ui.components.PrimaryButton
import com.example.task.ui.components.SecondaryButton
import com.example.task.ui.components.TOATextField
import com.example.task.ui.core.VerticalSpacer
import com.example.task.ui.theme.TaskTheme
import com.example.task.R

private const val APP_LOGO_WIDTH_PERCENTAGE = 0.75F
/**
 * This composable maintains the entire screen for handling user login.
 *
 * @param[viewState] The current state of the screen to render.
 * @param[onUsernameChanged] A callback invoked when the user enters their username.
 * @param[onPasswordChanged] A callback invoked when the user enters their password.
 * @param[onLoginClicked] A callback invoked when the user clicks the login button.
 * @param[onSignUpClicked] A callback invoked when the user clicks the sign up button.
 */
@Composable
fun LoginContent(
    viewState: LoginViewState,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onSignUpClicked: () -> Unit,
) {
    Surface(
        color = MaterialTheme.colors.background,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.screen_padding)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.weight(1F))

            AppLogo()

            Spacer(modifier = Modifier.weight(1F))

            UsernameInput(
                text = viewState.userName,
                onTextChanged = onUsernameChanged,
            )

            VerticalSpacer(height = 12.dp)

            PasswordInput(
                text = viewState.password,
                onTextChanged = onPasswordChanged,
            )

            VerticalSpacer(height = 48.dp)

            LoginButton(
                onClick = onLoginClicked,
            )

            VerticalSpacer(height = 12.dp)

            SignUpButton(
                onClick = onSignUpClicked,
            )
        }
    }
}

@Composable
private fun SignUpButton(
    onClick: () -> Unit,
) {
    SecondaryButton(
        text = stringResource(R.string.sign_up),
        onClick = onClick,
    )
}

@Composable
private fun LoginButton(
    onClick: () -> Unit,
) {
    PrimaryButton(
        text = stringResource(R.string.log_in),
        onClick = onClick,
    )
}

@Composable
fun PasswordInput(
    text: String,
    onTextChanged: (String) -> Unit,
) {
    TOATextField(
        text = text,
        onTextChanged = onTextChanged,
        labelText = stringResource(R.string.password),
    )
}

@Composable
private fun UsernameInput(
    text: String,
    onTextChanged: (String) -> Unit,
) {
    TOATextField(
        text = text,
        onTextChanged = onTextChanged,
        labelText = stringResource(R.string.username),
    )
}

@Composable
private fun AppLogo() {
    Image(
        painterResource(id = R.drawable.io_toa_checkmark),
        contentDescription = stringResource(R.string.app_logo_content_description),
        modifier = Modifier
            .fillMaxWidth(APP_LOGO_WIDTH_PERCENTAGE),
    )
}

@Preview(
    name = "Night Mode - Empty",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode - Empty",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
@Suppress("UnusedPrivateMember")
private fun EmptyLoginContentPreview() {
    val viewState = LoginViewState(
        userName = "",
        password = "",
    )

    TaskTheme {
        LoginContent(
            viewState = viewState,
            onUsernameChanged = {},
            onPasswordChanged = {},
            onLoginClicked = {},
            onSignUpClicked = {},
        )
    }
}
