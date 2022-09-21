package com.example.task.login.domain.model

import com.example.task.login.domain.usecase.Email
import com.example.task.login.domain.usecase.Password

sealed class LoginType {
    data class Credentials(
        val email: Email,
        val password: Password,
    ) : LoginType()

    object Google : LoginType()
}