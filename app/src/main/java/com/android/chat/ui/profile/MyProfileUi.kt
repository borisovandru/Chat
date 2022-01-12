package com.android.chat.ui.profile

import com.android.chat.core.Abstract
import com.android.chat.ui.core.AbstractView

interface MyProfileUi : Abstract.UiObject {

    fun map(name: AbstractView.Text, login: AbstractView.Text, avatar: AbstractView.Image)

    class Base(
        private val userName: String,
        private val userLogin: String,
        private val photoUrl: String,
    ) : MyProfileUi {

        override fun map(
            name: AbstractView.Text,
            login: AbstractView.Text,
            avatar: AbstractView.Image,
        ) {
            name.show(userName)
            login.show(userLogin)
            avatar.load(photoUrl)
        }
    }
}