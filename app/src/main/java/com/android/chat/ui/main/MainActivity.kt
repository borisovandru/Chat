package com.android.chat.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import com.android.chat.R
import com.android.chat.databinding.ActivityMainBinding
import com.android.chat.ui.core.BaseActivity
import com.android.chat.ui.core.AbstractView
import android.view.View

class MainActivity : BaseActivity(), AbstractView {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = viewModel(MainViewModel::class.java, this)

        val listener: (item: MenuItem) -> Boolean = {
            viewModel.changeScreen(it.itemId)
            true
        }

        viewModel.observe(this) {
            binding.bottomNavView.setOnItemSelectedListener(null)
            if (it.id != R.id.chat_screen)//todo make better
                binding.bottomNavView.selectedItemId = it.id
            val fragment = viewModel.getFragment(it.id)
            if (supportFragmentManager.canReplace(fragment))
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit()
            binding.bottomNavView.setOnItemSelectedListener(listener)
        }
        viewModel.init()
    }
    override fun show() {
        binding.bottomNavView.visibility = View.VISIBLE
    }

    override fun hide() {
        binding.bottomNavView.visibility = View.GONE
    }
}

private fun FragmentManager.canReplace(fragment: BaseFragment<*>) =
    fragments.isEmpty() || !(fragments.last() as BaseFragment<*>).matches(fragment.name())