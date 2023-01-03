package com.example.task.login.domain.repository

import com.example.task.login.domain.model.Credentials
import com.example.task.login.domain.model.LoginResponse
import com.example.task.core.data.Result
import io.mockk.coEvery
import io.mockk.mockk


/**
 * A fake implementation of a [LoginRepository] that wraps all of our mock work.
 */
class FakeLoginRepository {
    val mock: LoginRepository = mockk()

    fun mockLoginWithCredentials(
        credentials: Credentials,
        result: Result<LoginResponse>,
    ) {
        coEvery {
            mock.login(credentials)
        } returns result
    }
}