package com.android.chat.ui.search

import com.android.chat.core.Abstract
import com.android.chat.ui.core.AbstractView

interface SearchUserListUi : Abstract.UiObject,
    Abstract.Object<Unit, Abstract.Mapper.Data<List<SearchUserUi>, Unit>> {

    class Base(private val list: List<SearchUserUi>) : SearchUserListUi {
        override fun map(mapper: Abstract.Mapper.Data<List<SearchUserUi>, Unit>) {
            mapper.map(list)
        }
    }
}

interface SearchUserUi {

    fun chat(chat: Chat) = Unit

    fun map(
        avatar: AbstractView.Image,
        userLogin: AbstractView.Text,
        userName: AbstractView.Text,
    ) = Unit

    class Base(
        private val id: String,
        private val name: String,
        private val photoUrl: String,
    ) : SearchUserUi {

        override fun chat(chat: Chat) = chat.startChatWith(id)

        override fun map(
            avatar: AbstractView.Image,
            userLogin: AbstractView.Text,
            userName: AbstractView.Text,
        ) {
            avatar.load(photoUrl)
            userLogin.map(id)
            userName.map(name)
        }
    }

    class Search : SearchUserUi
    class Empty : SearchUserUi
    class NoResults : SearchUserUi
}

interface Chat {

    fun startChatWith(userId: String)
}