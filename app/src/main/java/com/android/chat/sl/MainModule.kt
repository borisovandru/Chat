package com.android.chat.sl

import com.android.chat.sl.core.BaseModule
import com.android.chat.sl.core.CoreModule
import com.android.chat.ui.main.MainViewModel

class MainModule(private val coreModule: CoreModule) : BaseModule<MainViewModel> {
    override fun viewModel() = MainViewModel(
        coreModule.navigationCommunication()
    )
}