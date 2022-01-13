package com.android.chat.ui.chats

import com.android.chat.ui.core.Communication

interface ChatsCommunication : Communication<ChatsUi> {
    class Base : Communication.Base<ChatsUi>(), ChatsCommunication
}