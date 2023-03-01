package com.example.task.login.ui

import com.example.task.login.domain.model.Credentials

/**
 * A sealed class defining all possible states of our login screen.
 *
 * @property[credentials] The current credentials entered by the user.
 * @property[buttonsEnabled] If true, the buttons on the login screen can accept clicks,
 * false otherwise.
 */
sealed class LoginViewState(
    open val credentials: Credentials,
    open val buttonsEnabled: Boolean = true,
) {
    /**
     * The initial state of the screen with nothing input.
     */
    object Initial : LoginViewState(
        credentials = Credentials(),
    )

    /**
     * The state of the screen as the user is entering email information.
     */
    data class Active(
        override val credentials: Credentials,
    ) : LoginViewState(
        credentials = credentials,
    )

    /**
     * The state of the screen as the user is attempting to login.
     */
    data class Submitting(
        override val credentials: Credentials,
    ) : LoginViewState(
        credentials = credentials,
        buttonsEnabled = false,
    )

    /**
     * The state of the screen when there is an error logging in.
     */
    data class SubmissionError(
        override val credentials: Credentials,
        val errorMessage: String,
    ) : LoginViewState(
        credentials = credentials,
    )

    /**
     * The state of the screen when the user tries to submit with invalid inputs.
     */
    data class InputError(
        override val credentials: Credentials,
        val emailInputErrorMessage: String?,
        val passwordInputErrorMessage: String?,
    ) : LoginViewState(
        credentials = credentials,
    )
}
