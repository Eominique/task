package com.example.task.login.ui

import androidx.lifecycle.ViewModel
import com.example.task.login.domain.model.Credentials
import com.example.task.login.domain.model.Email
import com.example.task.login.domain.model.Password
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {

    private val _viewState: MutableStateFlow<LoginViewState> =
        MutableStateFlow(LoginViewState.Initial)
    val viewState: StateFlow<LoginViewState> = _viewState

    fun emailChanged(email: String) {
        val currentCredentials = _viewState.value.credentials

        _viewState.value = LoginViewState.Active(
            currentCredentials.withUpdatedEmail(email)
        )
    }

    fun passwordChanged(password: String) {
        val currentCredentials = _viewState.value.credentials

        _viewState.value = LoginViewState.Active(
            currentCredentials.withUpdatedPassword(password)
        )
    }

    fun loginButtonClicked() {

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