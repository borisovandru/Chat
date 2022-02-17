package com.android.chat.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.FragmentManager
import com.android.chat.R
import com.android.chat.databinding.ActivityMainBinding
import com.android.chat.ui.core.AbstractView
import com.android.chat.ui.core.BaseActivity

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
            val isBaseLevel = it.isBaseLevel()
            if (isBaseLevel)
                binding.bottomNavView.selectedItemId = it.data()
            val fragment = viewModel.getFragment(it.data())
            if (supportFragmentManager.canReplace(fragment)) {
                val transaction = supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                if (!isBaseLevel)
                    transaction.addToBackStack(fragment.name())
                transaction.commit()
            }
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