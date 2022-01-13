package com.android.chat.data.chat

import com.android.chat.core.Abstract
import com.android.chat.domain.chat.MessageDomain
import com.android.chat.domain.chat.MessagesDomain

interface MessagesDataMapper<T> : Abstract.Mapper.DataToDomain<List<MessageData>, T>

class BaseMessagesDataMapper : MessagesDataMapper<MessagesDomain> {
    override fun map(data: List<MessageData>): MessagesDomain {
        return MessagesDomain.Success(
            data.map {
                if (it.isMyMessage())
                    MessageDomain.MyMessageDomain(it.messageBody())
                else
                    MessageDomain.UserMessageDomain(it.messageBody())
            }
        )
    }

    override fun map(e: Exception): MessagesDomain {
        return MessagesDomain.Fail(e.message ?: "error")
    }
}