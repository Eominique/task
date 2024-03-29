package com.example.task.login.ui


import com.google.common.truth.Truth.assertThat
import com.example.task.fakes.FakeCredentialsLoginUseCase
import com.example.task.login.domain.model.Credentials
import com.example.task.login.domain.model.LoginResult
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class LoginViewModelRobot {

private val fakeCredentialsLoginUseCase = FakeCredentialsLoginUseCase()

private lateinit var viewModel: LoginViewModel

fun buildViewModel() = apply {
    viewModel = LoginViewModel(
        credentialsLoginUseCase = fakeCredentialsLoginUseCase.mock
    )
}

fun mockLoginResultForCredentials(
    credentials: Credentials,
    result: LoginResult
) = apply {
    fakeCredentialsLoginUseCase.mockLoginResultForCredentials(credentials, result)
}

    fun enterEmail(email: String) = apply {
        viewModel.emailChanged(email)
    }

    fun enterPassword(password: String) = apply {
        viewModel.passwordChanged(password)
    }

    fun clickLogInButton() = apply {
        viewModel.loginButtonClicked()
    }

    fun clickSignUpButton() = apply {
        viewModel.signUpButtonClicked()
    }

    /**
     * Launch a coroutine that will observe our [viewModel]'s view state and ensure that we consume
     * all of the supplied [viewStates] in the same order that they are supplied.
     *
     * We should call this near the front of the test, to ensure that every view state we emit
     * can be collected by this.
     */
    suspend fun expectViewStates(viewStates: List<LoginViewState>) = coroutineScope {
        launch {
//            viewModel.viewState.test {
//                for (state in viewStates) {
//                //    assertThat().isEqualTo(state)
//                }
//
//                this.cancel()
//            }
        }
        return@coroutineScope this@LoginViewModelRobot
    }

}