package com.example.task.login.domain.model


sealed class LoginType {
    data class Credentials(
        val email: Email,
        val password: Password,
    ) : LoginType()

    object Google : LoginType()
}