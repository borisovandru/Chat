package com.android.chat.domain.login

import com.android.chat.core.SaveText
import com.android.chat.data.LoginRepository
import com.android.chat.ui.login.Auth
import com.android.chat.ui.login.LoginWrapper

interface LoginInteractor {

    fun authorized(): Boolean

    suspend fun login(loginWrapper: LoginWrapper): Auth

    class Base(private val repository: LoginRepository) : LoginInteractor {

        override suspend fun login(loginWrapper: LoginWrapper): Auth {
            val result = loginWrapper.login()
            if (result is Auth.Base)
                result.map(SaveText(repository))
            return result
        }

        override fun authorized() = repository.user() != null
    }
}