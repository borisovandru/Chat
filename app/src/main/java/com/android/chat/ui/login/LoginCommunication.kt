package com.android.chat.ui.login

import com.android.chat.ui.core.Communication

interface LoginCommunication : Communication<LoginUi> {

    class Base : Communication.Base<LoginUi>(), LoginCommunication
}