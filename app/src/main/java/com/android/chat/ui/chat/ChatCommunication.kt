package com.android.chat.ui.chat

import com.android.chat.ui.core.Communication

interface ChatCommunication : Communication<List<MessageUi>> {
    class Base : Communication.Base<List<MessageUi>>(), ChatCommunication
}