package com.android.chat.data.chat

class ChatId(private val first: String, private val second: String) {

    fun value() = if (first > second)
        "${first}_$second"
    else
        "${second}_$first"
}