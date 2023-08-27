package com.example.task.login.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.task.core.ui.components.PrimaryButton
import com.example.task.R
import com.example.task.core.ui.components.SecondaryButton
import com.example.task.core.ui.components.TaskTextField
import com.example.task.core.ui.components.UIText
import com.example.task.core.ui.components.VerticalSpacer
import com.example.task.core.ui.theme.TaskTheme
import com.example.task.login.domain.model.Credentials
import com.example.task.login.domain.model.Email
import com.example.task.login.domain.model.Password
import com.example.task.core.ui.components.getString

private const val APP_LOGO_WIDTH_PERCENTAGE = 0.75F

/**
 * This composable maintains the entire screen for handling user login.
 *
 * @param[viewState] The current state of the screen to render.
 * @param[onEmailChanged] A callback invoked when the user enters their email.
 * @param[onPasswordChanged] A callback invoked when the user enters their password.
 * @param[onLoginClicked] A callback invoked when the user clicks the login button.
 * @param[onSignUpClicked] A callback invoked when the user clicks the sign up button.
 */
@Composable
fun LoginContent(
    viewState: LoginViewState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onSignUpClicked: () -> Unit,
) {
    Surface(
        color = MaterialTheme.colors.background,
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LogoInputsColumn(
                viewState,
                onEmailChanged,
                onPasswordChanged,
                onLoginClicked,
                onSignUpClicked
            )

            CircularProgressIndicator(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun LogoInputsColumn(
    viewState: LoginViewState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onSignUpClicked: () -> Unit
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

        EmailInput(
            text = viewState.credentials.email.value,
            onTextChanged = onEmailChanged,
            errorMessage = (viewState as? LoginViewState.Active)
                ?.emailInputErrorMessage
                ?.getString(),
        )

        VerticalSpacer(height = 12.dp)

        PasswordInput(
            text = viewState.credentials.password.value,
            onTextChanged = onPasswordChanged,
            errorMessage = (viewState as? LoginViewState.Active)
                ?.passwordInputErrorMessage
                ?.getString(),
        )

        if (viewState is LoginViewState.SubmissionError) {
            Text(
                text = viewState.errorMessage.getString(),
                color = MaterialTheme.colors.error,
                modifier = Modifier
                    .padding(top = 12.dp)
            )
        }

        VerticalSpacer(height = 48.dp)

        LoginButton(
            onClick = onLoginClicked,
            enabled = viewState.buttonsEnabled
        )

        VerticalSpacer(height = 12.dp)

        SignUpButton(
            onClick = onSignUpClicked,
            enabled = viewState.buttonsEnabled
        )
    }
}


@Composable
private fun SignUpButton(
    onClick: () -> Unit,
    enabled: Boolean
) {
    SecondaryButton(
        text = stringResource(R.string.sign_up),
        onClick = onClick,
        enabled = enabled
    )
}

@Composable
private fun LoginButton(
    onClick: () -> Unit,
    enabled: Boolean
) {
    PrimaryButton(
        text = stringResource(R.string.log_in),
        onClick = onClick,
        enabled = enabled
    )
}

@Composable
fun PasswordInput(
    text: String,
    onTextChanged: (String) -> Unit,
    errorMessage: String?
) {
    TaskTextField(
        text = text,
        onTextChanged = onTextChanged,
        labelText = stringResource(R.string.password),
        errorMessage = errorMessage,
        visualTransformation = PasswordVisualTransformation(),
    )
}

@Composable
private fun EmailInput(
    text: String,
    onTextChanged: (String) -> Unit,
    errorMessage: String?,
) {
    TaskTextField(
        text = text,
        onTextChanged = onTextChanged,
        labelText = stringResource(R.string.email),
        errorMessage = errorMessage,
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
@Suppress("UnusedPrivateMember")
@Composable
private fun LoginContentPreview(
    @PreviewParameter(LoginViewStateProvider::class)
    loginViewState: LoginViewState,
) {
    TaskTheme {
        LoginContent(
            viewState = loginViewState,
            onEmailChanged = {},
            onPasswordChanged = {},
            onLoginClicked = {},
            onSignUpClicked = {},
        )
    }
}

class LoginViewStateProvider : PreviewParameterProvider<LoginViewState> {

    override val values: Sequence<LoginViewState>
        get() {
            val activeCredentials = Credentials(
                Email("testy@mctestface.com"),
                Password("Hunter2"),
            )

            return sequenceOf(
                LoginViewState.Initial,
                LoginViewState.Active(activeCredentials),
                LoginViewState.Submitting(activeCredentials),
                LoginViewState.SubmissionError(
                    credentials = activeCredentials,
                    errorMessage = UIText.StringText("Something went wrong."),
                ),
                LoginViewState.Active(
                    credentials = activeCredentials,
                    emailInputErrorMessage = UIText.StringText("Please enter an email."),
                    passwordInputErrorMessage = UIText.StringText("Please enter a password"),
                ),
            )
        }
}
