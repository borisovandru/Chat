package com.android.chat.ui.login

import com.android.chat.data.login.UserInitial

interface Auth {

    fun <T> map(mapper: AuthResultMapper<T>): T

    data class Base(private val profile: Map<String, Any>) : Auth {
        override fun <T> map(mapper: AuthResultMapper<T>): T = mapper.map(profile)
    }

    data class Fail(val e: Exception) : Auth {
        override fun <T> map(mapper: AuthResultMapper<T>): T = mapper.map(emptyMap())//todo
    }

    interface AuthResultMapper<T> {
        fun map(profile: Map<String, Any>): T

        class Base : AuthResultMapper<UserInitial> {

            override fun map(profile: Map<String, Any>) = UserInitial(
                profile["name"].toString(),
                profile["login"].toString().lowercase(),
                profile["email"].toString(),
                profile["bio"].toString(),
                profile["avatar_url"].toString()
            )
        }
    }
}