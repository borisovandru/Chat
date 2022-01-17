package com.android.chat.ui.chats

import com.android.chat.ui.core.Communication

interface ChatsCommunication : Communication<List<ChatUi>> {
    class Base : Communication.Base<List<ChatUi>>(), ChatsCommunication
}