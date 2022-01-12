package com.android.chat.sl

import com.android.chat.data.login.LoginRepository
import com.android.chat.domain.login.LoginInteractor
import com.android.chat.sl.core.BaseModule
import com.android.chat.ui.login.Auth
import com.android.chat.ui.login.LoginCommunication
import com.android.chat.ui.login.LoginViewModel

class LoginModule : BaseModule<LoginViewModel> {

    override fun viewModel() = LoginViewModel(
        LoginCommunication.Base(),
        LoginInteractor.Base(
            LoginRepository.Base(),
            Auth.AuthResultMapper.Base()
        )
    )
}
