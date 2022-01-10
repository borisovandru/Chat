package com.android.chat.ui.core

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.android.chat.sl.core.ChatApp

abstract class BaseActivity : AppCompatActivity() {

    protected fun <T : ViewModel> viewModel(model: Class<T>, owner: ViewModelStoreOwner) =
        (application as ChatApp).viewModel(model, owner)
}