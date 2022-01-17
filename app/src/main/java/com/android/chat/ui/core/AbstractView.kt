package com.android.chat.ui.core

import com.android.chat.ui.chat.TextMapper

interface AbstractView {

    fun show()
    fun hide()

    interface Text : AbstractView, TextMapper.Void

    interface Image : AbstractView {

        fun load(url: String)
    }
}