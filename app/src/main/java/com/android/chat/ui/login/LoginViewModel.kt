package com.android.chat.ui.login

import androidx.lifecycle.viewModelScope
import com.android.chat.domain.login.LoginInteractor
import com.android.chat.ui.core.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    communication: LoginCommunication,
    private val interactor: LoginInteractor,
    private val dispatchersIO: CoroutineDispatcher = Dispatchers.IO,
    private val dispatchersMain: CoroutineDispatcher = Dispatchers.Main,
) : BaseViewModel<LoginCommunication, LoginUi>(communication) {

    fun login(login: LoginEngine) {
        communication.map(LoginUi.Progress())
        viewModelScope.launch(dispatchersIO) {
            val result = interactor.login(login)
            val resultUi = if (result is Auth.Fail)
                LoginUi.Failed(result.e.message ?: "")//todo improve it
            else
                LoginUi.Success
            withContext(dispatchersMain) { communication.map(resultUi) }
        }
    }

    fun init(signIn: LoginEngine) {
        if (interactor.authorized()) {
            communication.map(LoginUi.Progress())
            viewModelScope.launch(dispatchersIO) {
                val result = interactor.signIn(signIn)
                val resultUi = if (result is Auth.Fail)
                    LoginUi.Failed(result.e.message ?: "")//todo improve it
                else
                    LoginUi.Success
                withContext(dispatchersMain) { communication.map(resultUi) }
            }
        } else {
            communication.map(LoginUi.Initial())
        }
    }
}