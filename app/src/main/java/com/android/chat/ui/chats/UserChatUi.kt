package com.android.chat.ui.chats

import com.android.chat.ui.core.AbstractView
import com.android.chat.ui.search.Chat

interface UserChatUi {
    fun map(userName: AbstractView.Text, userAvatar: AbstractView.Image)
    fun same(userData: UserChatUi): Boolean
    fun startChat(chat: Chat)

    data class Base(
        private val id: String,
        private val name: String,
        private val avatarUrl: String
    ) : UserChatUi {

        override fun map(
            userName: AbstractView.Text,
            userAvatar: AbstractView.Image
        ) {
            userName.map(name)
            userAvatar.load(avatarUrl)
        }

        override fun same(userData: UserChatUi) = userData is Base && userData.id == id
        override fun startChat(chat: Chat) = chat.startChatWith(id)
    }
}