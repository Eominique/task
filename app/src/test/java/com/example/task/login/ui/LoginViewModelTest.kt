package com.example.task.login.ui
import com.example.task.login.domain.model.Credentials
import com.example.task.login.domain.model.Email
import com.example.task.login.domain.model.Password
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {

    private lateinit var testRobot: LoginViewModelRobot

    private val defaultCredentials = Credentials(
        Email("testy@mctestface.com"),
        Password("Hunter2"),
    )

    @Before
    fun setUp() {
        testRobot = LoginViewModelRobot()
    }

    @Test
    fun testInitialState() {
        testRobot
            .buildViewModel()
            .assertViewState(LoginViewState.Initial)
    }

    @Test
    fun testUpdateCredentials() {
        val credentials = defaultCredentials

        testRobot
            .buildViewModel()
            .enterEmail(credentials.email.value)
            .enterPassword(credentials.password.value)
            .assertViewState(LoginViewState.Active(credentials))
    }

}