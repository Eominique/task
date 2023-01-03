package com.example.task.login.domain.repository

import com.example.task.login.domain.model.Credentials
import com.example.task.login.domain.model.LoginResponse
import com.example.task.login.domain.model.LoginType
import com.example.task.core.data.Result

/**
 * The data layer for any requests related to logging in the user.
 */

interface LoginRepository {

    /**
     * Given some user [credentials], try to login the user.
     *
     * @return A [Result] that contains the [LoginResponse] if successful, or an error otherwise.
     */
    suspend fun login(
        credentials: Credentials
    ): Result<LoginResponse>
}