package com.android.chat.sl.login

import com.android.chat.data.LoginRepository
import com.android.chat.domain.login.LoginInteractor
import com.android.chat.sl.core.BaseModule
import com.android.chat.sl.core.CoreModule
import com.android.chat.ui.login.LoginCommunication
import com.android.chat.ui.login.LoginViewModel

class LoginModule(private val coreModule: CoreModule) : BaseModule<LoginViewModel> {

    override fun viewModel() = LoginViewModel(
        LoginCommunication.Base(),
        LoginInteractor.Base(
            LoginRepository.Base(coreModule.provideSharedPreferences())
        )
    )
}
