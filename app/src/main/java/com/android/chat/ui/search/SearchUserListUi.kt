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
    fun map(
        avatar: AbstractView.Image,
        userLogin: AbstractView.Text,
        userName: AbstractView.Text,
    ) = Unit

    class Base(
        private val id: String,//todo use to open profile
        private val name: String,
        private val photoUrl: String,
    ) : SearchUserUi {

        override fun map(
            avatar: AbstractView.Image,
            userLogin: AbstractView.Text,
            userName: AbstractView.Text,
        ) {
            avatar.load(photoUrl)
            userLogin.show(id)
            userName.show(name)
        }
    }

    class Search : SearchUserUi
    class Empty : SearchUserUi
    class NoResults : SearchUserUi
}