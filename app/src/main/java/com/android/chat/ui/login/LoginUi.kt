package com.android.chat.ui.login

import com.android.chat.core.Abstract
import com.android.chat.ui.core.AbstractView

interface LoginUi : Abstract.UiObject {

    fun map(error: AbstractView.Text, progress: AbstractView, button: AbstractView) = Unit

    object Success : LoginUi

    object Initial : LoginUi {
        override fun map(error: AbstractView.Text, progress: AbstractView, button: AbstractView) {
            error.hide()
            progress.hide()
            button.show()
        }
    }

    class Progress : LoginUi {
        override fun map(error: AbstractView.Text, progress: AbstractView, button: AbstractView) {
            error.hide()
            progress.show()
            button.hide()
        }
    }

    data class Failed(private val message: String) : LoginUi {
        override fun map(error: AbstractView.Text, progress: AbstractView, button: AbstractView) {
            error.show(message)
            progress.hide()
            button.show()
        }
    }
}