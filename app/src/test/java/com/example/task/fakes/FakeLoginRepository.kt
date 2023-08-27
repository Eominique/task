package com.example.task.fakes

import com.example.task.login.domain.model.Credentials
import com.example.task.login.domain.model.LoginResponse
import com.example.task.login.domain.repository.LoginRepository
import com.example.task.core.data.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk

/**
 * A fake implementation of a [LoginRepository] that wraps all of our mock work.
 */
class FakeLoginRepository {

    val mock: LoginRepository = mockk()

    fun mockLoginWithCredentials(
        credentials: Credentials,
        result: Result<LoginResponse>
    ) {
        coEvery {
            mock.login(credentials)
        }
        return result as Unit
    }

    fun verifyNoLoginCall() {
        coVerify(exactly = 0) {
            mock.login(any())
        }
    }

}