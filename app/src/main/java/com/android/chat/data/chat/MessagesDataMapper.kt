package com.android.chat.data.chat

import com.android.chat.core.Abstract
import com.android.chat.domain.chat.MessageDomain
import com.android.chat.domain.chat.MessagesDomain

interface MessagesDataMapper<T> : Abstract.Mapper.DataToDomain<List<Pair<String, MessageData>>, T>

class BaseMessagesDataMapper : MessagesDataMapper<MessagesDomain> {
    override fun map(data: List<Pair<String, MessageData>>) =
        MessagesDomain.Success(data.map { (id, data) ->
            if (data.messageIsMine())
                MessageDomain.MyMessageDomain(data.messageBody(), data.wasReadByUser())
            else
                MessageDomain.UserMessageDomain(id, data.messageBody(), data.wasReadByUser())
        })

    override fun map(e: Exception): MessagesDomain {
        return MessagesDomain.Fail(e.message ?: "error")
    }
}