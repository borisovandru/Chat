package com.android.chat.sl

import com.android.chat.sl.core.BaseModule
import com.android.chat.ui.main.MainViewModel
import com.android.chat.ui.main.NavigationCommunication

class MainModule : BaseModule<MainViewModel> {
    override fun viewModel() = MainViewModel(
        NavigationCommunication.Base()
    )
}