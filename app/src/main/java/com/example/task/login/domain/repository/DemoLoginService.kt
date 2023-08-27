package com.example.task.login.domain.repository

import com.example.task.core.data.Result
import com.example.task.login.domain.model.*
import javax.inject.Inject

/**
 * This is a sample [LoginRepository] that does not interact with any real data source, but allows
 * us to quickly modify return values for manual testing sake.
 */
class DemoLoginService @Inject constructor() : LoginRepository {
    override suspend fun login(credentials: Credentials): Result<LoginResponse> {
        val defaultToken = Token(
            AuthToken(""),
            RefreshToken("")
        )

        val defaultResponse = LoginResponse(defaultToken)

        return Result.Success(defaultResponse)
    }

}