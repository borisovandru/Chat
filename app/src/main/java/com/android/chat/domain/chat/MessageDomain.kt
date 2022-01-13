package com.android.chat.domain.chat

import com.android.chat.ui.chat.MessageDomainToUiMapper

interface MessageDomain {

    fun <T> map(mapper: MessageDomainToUiMapper<T>): T

    data class MyMessageDomain(private val text: String) : MessageDomain {
        override fun <T> map(mapper: MessageDomainToUiMapper<T>): T {
            return mapper.map(text, true)
        }
    }

    data class UserMessageDomain(private val text: String) : MessageDomain {
        override fun <T> map(mapper: MessageDomainToUiMapper<T>): T {
            return mapper.map(text, false)
        }
    }
}