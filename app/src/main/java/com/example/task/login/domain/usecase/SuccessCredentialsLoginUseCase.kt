package com.example.task.login.domain.usecase

import com.example.task.login.domain.model.LoginResult

/**
 * This is a temporary class to create a mock implementation of [CredentialsLoginUseCase] that is
 * always successful
 */
class SuccessCredentialsLoginUseCase : CredentialsLoginUseCase {

    override suspend fun invoke(email: Email, password: Password): LoginResult {
        return LoginResult.Success
    }
}