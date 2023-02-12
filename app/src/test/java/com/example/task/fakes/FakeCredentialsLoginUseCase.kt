package com.example.task.fakes

import com.example.task.login.domain.model.Credentials
import com.example.task.login.domain.model.LoginResult
import com.example.task.login.domain.usecase.CredentialsLoginUseCase
import io.mockk.coEvery
import io.mockk.mockk

class FakeCredentialsLoginUseCase {

val mock: CredentialsLoginUseCase = mockk()

fun mockLoginResultForCredentials(
    credentials: Credentials,
    result: LoginResult
){
    coEvery {
        mock(credentials)
    }
    return result as Unit
}















}