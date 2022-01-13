package com.android.chat.domain.chat

import com.android.chat.data.chat.ChatRepository
import com.android.chat.data.chat.MessagesData
import com.android.chat.data.chat.MessagesDataMapper
import com.android.chat.ui.chat.MessagesRealtimeUpdateCallback

interface ChatInteractor {

    suspend fun send(message: String): Boolean

    fun stopGettingUpdates()
    fun startGettingUpdates(callback: MessagesRealtimeUpdateCallback)

    class Base(
        private val repository: ChatRepository,
        private val mapper: MessagesDataMapper<MessagesDomain>
    ) : ChatInteractor {

        private var callback: MessagesRealtimeUpdateCallback = MessagesRealtimeUpdateCallback.Empty

        private val dataCallback = object : MessagesDataRealtimeUpdateCallback {
            override fun updateMessages(messagesData: MessagesData) {
                callback.updateMessages(messagesData.map(mapper))
            }
        }

        override suspend fun send(message: String) = try {
            repository.sendMessage(message)
        } catch (e: Exception) {
            false
        }

        override fun stopGettingUpdates() {
            callback = MessagesRealtimeUpdateCallback.Empty
            repository.stopGettingUpdates()
        }

        override fun startGettingUpdates(callback: MessagesRealtimeUpdateCallback) {
            this.callback = callback
            repository.startGettingUpdates(dataCallback)
        }
    }
}

interface MessagesDataRealtimeUpdateCallback {
    fun updateMessages(messagesData: MessagesData)

    object Empty : MessagesDataRealtimeUpdateCallback {
        override fun updateMessages(messagesData: MessagesData) = Unit
    }
}