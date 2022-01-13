package com.android.chat.ui.chat

import com.android.chat.domain.chat.MessageDomain
import com.android.chat.domain.chat.MessagesDomainToUiMapper

class BaseMessagesDomainToUiMapper(private val mapper: MessageDomainToUiMapper<MessageUi>) :
    MessagesDomainToUiMapper<List<MessageUi>> {

    override fun map(data: List<MessageDomain>) = data.map { it.map(mapper) }

    override fun map(error: String): List<MessageUi> {
        return listOf(MessageUi.Mine(error, MyMessageUiState.FAILED))//todo make better later
    }
}

interface MessageDomainToUiMapper<T> {
    fun map(text: String, isMyMessage: Boolean): T
    fun map(error: String): T

    class Base : MessageDomainToUiMapper<MessageUi> {
        override fun map(text: String, isMyMessage: Boolean) = if (isMyMessage)
            MessageUi.Mine(text, MyMessageUiState.SENT)//todo also read from domain
        else
            MessageUi.FromUser(text)

        override fun map(error: String) = MessageUi.Mine(error, MyMessageUiState.FAILED)
    }
}