package com.example.task.login.domain.usecase

import com.example.task.core.data.Result
import com.example.task.fakes.FakeLoginRepository
import com.example.task.fakes.FakeTokenRepository
import com.example.task.login.domain.model.*
import com.example.task.login.domain.model.Email
import com.example.task.login.domain.model.Password
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test


class ProdCredentialsLoginUseCaseTest {
    private val defaultCredentials = Credentials(
        email = Email("testy@mctestface.com"),
        password = Password("Hunter2"),
    )

    private val defaultToken = Token(
        authToken = AuthToken("Auth"),
        refreshToken = RefreshToken("Refresh"),
    )

    private lateinit var loginRepository: FakeLoginRepository
    private lateinit var tokenRepository: FakeTokenRepository

    @Before
    fun setUp() {
        loginRepository = FakeLoginRepository()
        tokenRepository = FakeTokenRepository()
    }

    @Test
    fun testSuccessfulLogin() = runBlockingTest {
        val loginResponse = Result.Success(
            LoginResponse(
                token = defaultToken,
            )
        )

        loginRepository.mockLoginWithCredentials(
            defaultCredentials,
            loginResponse,
        )

        val useCase = ProdCredentialsLoginUseCase(
            loginRepository = loginRepository.mock,
            tokenRepository = tokenRepository.mock,
        )

        val result = useCase(defaultCredentials)
        assertThat(result).isEqualTo(LoginResult.Success)
        tokenRepository.verifyTokenStored(defaultToken)
    }

    @Test
    fun testUnknownFailureLogin() = runBlockingTest {
        val loginResponse: Result<LoginResponse> = Result.Error(
            Throwable("Adam fucked up")
        )

        loginRepository.mockLoginWithCredentials(
            defaultCredentials,
            loginResponse,
        )

        val useCase = ProdCredentialsLoginUseCase(
            loginRepository = loginRepository.mock,
            tokenRepository = tokenRepository.mock,
        )

        val result = useCase(defaultCredentials)
        assertThat(result).isEqualTo(LoginResult.Failure.Unknown)
        tokenRepository.verifyNoTokenStored()
    }

    @Test
    fun testInvalidCredentialLogin() = runBlockingTest {
        val loginResponse: Result<LoginResponse> = Result.Error(
            InvalidCredentialsException()
        )

        loginRepository.mockLoginWithCredentials(
            defaultCredentials,
            loginResponse,
        )

        val useCase = ProdCredentialsLoginUseCase(
            loginRepository = loginRepository.mock,
            tokenRepository = tokenRepository.mock,
        )

        val result = useCase(defaultCredentials)
        assertThat(result).isEqualTo(LoginResult.Failure.InvalidCredentials)
        tokenRepository.verifyNoTokenStored()
    }

    @Test
    fun testEmptyCredentialLogin() = runBlockingTest {
val emptyCredentials = Credentials()

        val useCase = ProdCredentialsLoginUseCase(
            loginRepository = loginRepository.mock,
            tokenRepository = tokenRepository.mock
        )

        val result = useCase(emptyCredentials)
        assertThat(result).isEqualTo(
            LoginResult.Failure.EmptyCredentials(
                emptyEmail = true,
                emptyPassword = true
            )
        )
        loginRepository.verifyNoLoginCall()
        tokenRepository.verifyNoTokenStored()
    }

}

