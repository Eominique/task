package com.example.task.login.domain.usecase

import com.example.task.login.domain.model.Credentials
import com.example.task.login.domain.model.InvalidCredentialsException
import com.example.task.login.domain.model.LoginResult
import com.example.task.core.data.Result

import com.example.task.login.domain.repository.LoginRepository
import com.example.task.login.domain.repository.TokenRepository

/**
 * A concrete implementation of a [CredentialsLoginUseCase] that will request logging in
 * via the [loginRepository].
 */
class ProdCredentialsLoginUseCase(
    private val loginRepository: LoginRepository,
    private val tokenRepository: TokenRepository,
) : CredentialsLoginUseCase {

    override suspend fun invoke(credentials: Credentials): LoginResult {
        val repoResult = loginRepository.login(credentials)

        return when (repoResult) {
            is Result.Success -> {
                tokenRepository.storeToken(repoResult.data.token)
                return LoginResult.Success
            }
            is Result.Error -> {
                loginResultForError(repoResult)
            }

        }
    }

    /**
     * Checks the possible error scenarios for the [repoResult] and maps to an appropriate
     * [LoginResult.Failure].
     */
    private fun loginResultForError(repoResult: Result.Error): LoginResult.Failure {
        return when (repoResult.error) {
            is InvalidCredentialsException -> {
                LoginResult.Failure.InvalidCredentials
            }
            else -> {
                LoginResult.Failure.Unknown
            }
        }
    }
}
