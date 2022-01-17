package com.android.chat.core

interface Match<T> {

    fun matches(data:T) : Boolean
}