package com.example.task.login.domain.model

@JvmInline
value class Email(val value: String)

@JvmInline
value class Password(val value: String)

data class Credentials(
    val email: Email,
    val password: Password,
)