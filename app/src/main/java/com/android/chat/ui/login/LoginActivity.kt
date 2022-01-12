package com.android.chat.ui.login

import android.os.Bundle
import com.android.chat.databinding.ActivityLoginBinding
import com.android.chat.ui.core.BaseActivity

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel = viewModel(LoginViewModel::class.java, this)
        viewModel.observe(this) {

                if (it is LoginUi.Success)
                    switchToMain()
                else
                    it.map(binding.errorTextView, binding.progressBar, binding.loginButton)
        }

        binding.loginButton.setOnClickListener { viewModel.login(LoginEngine.Login(this)) }
        viewModel.init(LoginEngine.SignIn(this))
    }
}