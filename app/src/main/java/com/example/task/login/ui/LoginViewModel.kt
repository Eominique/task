package com.example.task.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.R
import com.example.task.core.ui.components.UIText
import com.example.task.login.domain.model.Credentials
import com.example.task.login.domain.model.Email
import com.example.task.login.domain.model.LoginResult
import com.example.task.login.domain.model.Password
import com.example.task.login.domain.usecase.CredentialsLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val credentialsLoginUseCase: CredentialsLoginUseCase
) : ViewModel() {

    private val _viewState: MutableStateFlow<LoginViewState> =
        MutableStateFlow(LoginViewState.Initial)
    val viewState: StateFlow<LoginViewState> = _viewState

    fun emailChanged(email: String) {
        val currentCredentials = _viewState.value.credentials
        val currentPasswordErrorMessage = (_viewState.value as? LoginViewState.Active)
            ?.passwordInputErrorMessage

        _viewState.value = LoginViewState.Active(
            currentCredentials.withUpdatedEmail(email),
            emailInputErrorMessage = null,
            passwordInputErrorMessage = currentPasswordErrorMessage
        )
    }

    fun passwordChanged(password: String) {
        val currentCredentials = _viewState.value.credentials
        val currentEmailErrorMessage = (_viewState.value as? LoginViewState.Active)
            ?.emailInputErrorMessage

        _viewState.value = LoginViewState.Active(
            credentials = currentCredentials.withUpdatedPassword(password),
            passwordInputErrorMessage = null,
            emailInputErrorMessage = currentEmailErrorMessage
        )
    }

    fun loginButtonClicked() {
        val currentCredentials = _viewState.value.credentials

        _viewState.value = LoginViewState.Submitting(
            credentials = currentCredentials
        )

        viewModelScope.launch {
            val loginResult = credentialsLoginUseCase(currentCredentials)

            _viewState.value = when (loginResult) {
                is LoginResult.Failure.InvalidCredentials -> {
                    LoginViewState.SubmissionError(
                        credentials = currentCredentials,
                        errorMessage = UIText.ResourceText(R.string.err_invalid_credentials),
                    )
                }
                is LoginResult.Failure.Unknown -> {
                    LoginViewState.SubmissionError(
                        credentials = currentCredentials,
                        errorMessage = UIText.ResourceText(R.string.err_login_failure),
                    )
                }

                is LoginResult.Failure.EmptyCredentials -> {
                    loginResult.toLoginViewState(currentCredentials)
                }
                else -> _viewState.value
            }
        }
    }

    fun signUpButtonClicked() {

    }

}

private fun Credentials.withUpdatedEmail(email: String): Credentials {
    return this.copy(email = Email(email))
}

private fun Credentials.withUpdatedPassword(password: String): Credentials {
    return this.copy(password = Password(password))
}

private fun LoginResult.Failure.EmptyCredentials.toLoginViewState(
    credentials: Credentials
): LoginViewState {
    return LoginViewState.Active(
        credentials = credentials,
        emailInputErrorMessage = UIText.ResourceText(R.string.err_empty_email).takeIf {
            this.emptyEmail
        },
        passwordInputErrorMessage = UIText.ResourceText(R.string.err_empty_password).takeIf {
            this.emptyPassword
        }
    )
}