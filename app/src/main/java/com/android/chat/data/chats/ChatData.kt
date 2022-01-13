package com.android.chat.data.chats

import com.android.chat.data.chat.MessageData
import com.android.chat.domain.chats.ChatDomain

interface ChatData {

    fun <T> map(mapper: ChatDataMapper<T>): T

    data class Base(
        private val otherUserId: String,
        private val lastMessage: MessageData,
        private val notReadMessagesCount: Int
    ) : ChatData {
        override fun <T> map(mapper: ChatDataMapper<T>) =
            mapper.map(otherUserId, lastMessage, notReadMessagesCount)
    }
}

interface ChatDataMapper<T> {

    fun map(otherUserId: String, lastMessage: MessageData, notReadMessagesCount: Int): T

    class Base : ChatDataMapper<ChatDomain> {
        override fun map(
            otherUserId: String,
            lastMessage: MessageData,
            notReadMessagesCount: Int
        ) = if (lastMessage.messageIsMine())
            ChatDomain.LastMessageFromMe(
                otherUserId,
                lastMessage.messageBody(),
                notReadMessagesCount
            )
        else
            ChatDomain.LastMessageFromUser(
                otherUserId,
                lastMessage.messageBody(),
                notReadMessagesCount
            )
    }
}