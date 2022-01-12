package com.android.chat.ui.profile

import com.android.chat.ui.core.Communication

interface MyProfileCommunication : Communication<MyProfileUi> {
    class Base : Communication.Base<MyProfileUi>(), MyProfileCommunication
}