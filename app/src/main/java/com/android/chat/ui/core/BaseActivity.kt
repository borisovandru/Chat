package com.android.chat.ui.core

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.android.chat.sl.core.ChatApp
import com.android.chat.ui.login.LoginActivity
import com.android.chat.ui.main.MainActivity

abstract class BaseActivity : AppCompatActivity() {

    fun <T : ViewModel> viewModel(model: Class<T>, owner: ViewModelStoreOwner) =
        (application as ChatApp).viewModel(model, owner)

    fun switchToLogin() = switchTo(LoginActivity::class.java)
    fun switchToMain() = switchTo(MainActivity::class.java)

    private fun switchTo(clasz: Class<*>) {
        startActivity(Intent(this, clasz))
        finish()
    }
}