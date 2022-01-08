package com.android.chat.sl.core

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class ChatApp : Application() {

    private val factory by lazy {
        ViewModelsFactory(DependencyContainer.Base(coreModule))
    }

    private lateinit var coreModule: CoreModule

    override fun onCreate() {
        super.onCreate()
        coreModule = CoreModule.Base(this)
    }

    fun <T : ViewModel> viewModel(modelClass: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner, factory).get(modelClass)
}