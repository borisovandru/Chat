package com.android.chat.data.profile

import com.android.chat.data.login.UserInitial
import com.android.chat.ui.profile.MyProfileUi

interface MyProfileData {

    fun <T> map(mapper: MyProfileMapper<T>): T

    class Base(
        private val userInitial: UserInitial,
    ) : MyProfileData {
        override fun <T> map(mapper: MyProfileMapper<T>): T {
            return mapper.map(
                userInitial.name,
                "github.com/" + userInitial.login + "\n\n" + userInitial.bio,
                userInitial.photoUrl
            )
        }
    }

    interface MyProfileMapper<T> {

        fun map(name: String, login: String, photoUrl: String): T

        class Base : MyProfileMapper<MyProfileUi> {
            override fun map(name: String, login: String, photoUrl: String): MyProfileUi {
                return MyProfileUi.Base(name, login, photoUrl)
            }
        }
    }
}