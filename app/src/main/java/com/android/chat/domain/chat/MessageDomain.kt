package com.android.chat.domain.chat

import com.android.chat.ui.chat.MessageDomainToUiMapper

interface MessageDomain {

    fun <T> map(mapper: MessageDomainToUiMapper<T>): T

    data class MyMessageDomain(private val text: String, private val isRead: Boolean) :
        MessageDomain {
        override fun <T> map(mapper: MessageDomainToUiMapper<T>): T {
            return mapper.map("", isRead, text, true)
        }
    }

    data class UserMessageDomain(
        private val id: String,
        private val text: String,
        private val isRead: Boolean
    ) : MessageDomain {
        override fun <T> map(mapper: MessageDomainToUiMapper<T>): T {
            return mapper.map(id, isRead, text, false)
        }
    }
}