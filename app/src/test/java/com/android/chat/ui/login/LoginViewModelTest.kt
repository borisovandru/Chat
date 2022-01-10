package com.android.chat.ui.login

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.android.chat.domain.login.LoginInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.assertEquals
import org.junit.Test

class LoginViewModelTest {

    @ExperimentalCoroutinesApi
    @Test
    fun test_success() = runBlocking {
        val communication = TestCommunication()
        val dispatcher = TestCoroutineDispatcher()
        val viewModel = LoginViewModel(communication, TestInteractor(), dispatcher, dispatcher)
        viewModel.login(TestLoginWrapper(true))
        val actual = communication.loginUi
        val expected = LoginUi.Success
        assertEquals(expected, actual)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_fail() = runBlocking {
        val communication = TestCommunication()
        val dispatcher = TestCoroutineDispatcher()
        val viewModel = LoginViewModel(communication, TestInteractor(), dispatcher, dispatcher)
        viewModel.login(TestLoginWrapper(false))
        val actual = communication.loginUi
        val expected = LoginUi.Failed("error")
        assertEquals(expected, actual)
    }

    private inner class TestCommunication : LoginCommunication {
        var loginUi: LoginUi = LoginUi.Progress()

        override fun observe(owner: LifecycleOwner, observer: Observer<LoginUi>) = Unit
        override fun map(data: LoginUi) {
            loginUi = data
        }
    }

    private inner class TestInteractor : LoginInteractor {
        override fun authorized() = false
        override suspend fun login(loginWrapper: LoginWrapper) = loginWrapper.login()
    }

    private inner class TestLoginWrapper(private val success: Boolean) : LoginWrapper {
        override suspend fun login() =
            if (success) Auth.Base(emptyMap()) else Auth.Fail(IllegalStateException("error"))
    }
}