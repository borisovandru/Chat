package com.android.chat.ui.core

interface AbstractView {

    fun show()
    fun hide()

    interface Text : AbstractView {

        fun show(text: String)
    }
}