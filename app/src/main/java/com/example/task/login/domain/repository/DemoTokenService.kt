package com.example.task.login.domain.repository

import com.example.task.login.domain.model.Token
import javax.inject.Inject

/**
 * This is a sample [TokenRepository] that does not interact with any real data source, but allows
 * us to quickly modify return values for manual testing sake.
 */
class DemoTokenService  @Inject constructor(): TokenRepository{

    override suspend fun storeToken(token: Token) {
        TODO("Not yet implemented")
    }

    override suspend fun fetchToken(): Token? {
        TODO("Not yet implemented")
    }
}